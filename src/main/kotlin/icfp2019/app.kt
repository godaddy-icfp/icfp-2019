package icfp2019

import java.io.File

fun main(args: Array<String>) {
    File("/Users/jswenson/sources/personal/icfp-2019/problems/prob-001.desc").walk().forEach {
        if (it.isFile && it.extension.equals("desc")) {
            println("Running " + it.name)
            val problem = parseDesc(it.readText())
            val solution = solve(problem)
            File(it.nameWithoutExtension + ".sol").writeBytes(solution.toByteArray())
        }
    }
}

fun solve(problem: Problem): String {
    val strategy = BackTrackingStrategy()
    val gameBoardOf = GameBoard.gameBoardOf(problem)
    val gameState = GameState(gameBoardOf, listOf(RobotState(RobotId(0), problem.startingPosition)), listOf(), listOf())
    val actions = strategy.getActions(gameState)
    return Output.encodeRobotActions(actions)
}

fun constructObstacleMap(problem: Problem): Array<Array<Boolean>> {
    return problem.map.map { it.map { it.isObstacle }.toTypedArray() }.toTypedArray()
}
