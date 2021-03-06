package icfp2019.model

import icfp2019.core.*
import org.pcollections.PVector

typealias BoardCells = PVector<PVector<BoardCell>>
typealias BoardNodeStates = PVector<PVector<BoardNodeState>>

data class BoardCell(
    val point: Point,
    val isObstacle: Boolean = false,
    val hasTeleporterPlanted: Boolean = false
) {
    constructor(node: Node) : this(node.point, node.isObstacle, node.hasTeleporterPlanted)
}

data class BoardNodeState(
    val point: Point,
    val isWrapped: Boolean = false,
    val booster: Booster? = null
) {
    constructor(node: Node) : this(node.point, node.isWrapped, node.booster)

    val hasBooster: Boolean = booster != null

    fun hasBooster(booster: Booster): Boolean {
        if (this.booster != null) {
            return this.booster == booster
        }
        return false
    }
}

fun BoardCells.allCells(): Sequence<BoardCell> = this.asSequence().flatten().map { it!! }
fun BoardNodeStates.allStates(): Sequence<BoardNodeState> = this.asSequence().flatten().map { it!! }
fun BoardNodeStates.unwrappedNodeStates(): Sequence<BoardNodeState> = allStates().filter { !it.isWrapped }

data class GameState private constructor(
    private val board: BoardCells,
    private val boardState: BoardNodeStates,
    val mapSize: MapSize,
    val startingPoint: Point,
    private val robotStates: Map<RobotId, RobotState> = initialRobotMap(startingPoint),
    val teleportDestination: List<Point> = emptyList(),
    val unusedBoosters: Map<Booster, Int> = emptyMap()
) {

    constructor(problem: Problem) : this(
        initBoardNodes(problem.map),
        initBoardNodeState(problem),
        problem.size,
        problem.startingPosition
    )

    companion object {
        private fun initialRobotMap(point: Point) = mapOf(RobotId.first to RobotState(RobotId.first, point))

        private fun initBoardNodes(mapCells: MapCells): BoardCells {
            return mapCells.rebuild { BoardCell(it) }
        }

        private fun initBoardNodeState(problem: Problem): BoardNodeStates {
            return problem.map.rebuild {
                BoardNodeState(it)
            }
        }
    }

    fun initialize(): GameState {
        return this.wrapAffectedCells(RobotId.first)
    }

    fun board(): BoardCells = board
    fun boardState(): BoardNodeStates = boardState

    fun nodeState(point: Point): BoardNodeState = when {
        isInBoard(point) -> boardState.get(point)
        else -> error("$point Not in board")
    }

    val allRobotIds: Set<RobotId> = robotStates.keys

    fun get(point: Point): BoardCell {
        if (!isInBoard(point)) {
            throw ArrayIndexOutOfBoundsException("Access out of game board")
        }
        return board[point.x][point.y]
    }

    fun isGameComplete(): Boolean {
        return board().allCells().all { it.isObstacle || nodeState(it.point).isWrapped }
    }

    fun isInBoard(point: Point): Boolean {
        return (point.x in 0 until mapSize.x && point.y in 0 until mapSize.y)
    }

    fun backpackContains(booster: Booster): Boolean {
        return unusedBoosters.getOrDefault(booster, 0) > 0
    }

    fun updateBoard(point: Point, value: BoardCell): GameState {
        if (!isInBoard(point)) {
            throw ArrayIndexOutOfBoundsException("Access out of game board")
        }
        val newCells = board.update(point) { value }
        return copy(board = newCells)
    }

    fun updateState(point: Point, value: BoardNodeState): GameState {
        if (!isInBoard(point)) {
            throw ArrayIndexOutOfBoundsException("Access out of game board")
        }
        val newCells = boardState.update(point) { value }
        return copy(boardState = newCells)
    }

    fun withNewRobot(currentPosition: Point): GameState {
        val newId = this.allRobotIds.maxBy { it.id }!!.nextId()
        return withRobotState(newId, RobotState(newId, currentPosition))
    }

    fun withRobotPosition(robotId: RobotId, point: Point): GameState {
        return withRobotState(robotId, robot(robotId).copy(currentPosition = point))
    }

    fun withRobotState(robotId: RobotId, robotState: RobotState): GameState {
        return copy(robotStates = robotStates.plus(robotId to robotState))
    }

    fun robot(robotId: RobotId): RobotState {
        return this.robotStates.getValue(robotId)
    }

    fun boostersAvailable(booster: Booster): Int {
        return this.unusedBoosters.getOrDefault(booster, 0)
    }

    fun robotIsOn(robotId: RobotId, cloningLocation: Booster.CloningLocation): Boolean {
        return nodeState(robot(robotId).currentPosition).hasBooster(cloningLocation)
    }
}
