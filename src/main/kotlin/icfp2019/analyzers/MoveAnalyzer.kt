package icfp2019.analyzers

import icfp2019.model.*
import icfp2019.core.Analyzer

object MoveAnalyzer : Analyzer<(RobotId, Action) -> Boolean> {
    override fun analyze(map: GameBoard): (state: GameState) -> (RobotId, Action) -> Boolean {
        return { gameState ->
            { robotId, action ->
                var possible = false

                val robotState = gameState.robotState[robotId]
                if (robotState != null &&
                    map.isInBoard(robotState.currentPosition)) {
                    val cell = map.get(robotState.currentPosition)

                    fun hasBooster(hotTiles: HotTiles): Boolean {
                        return gameState.unusedHotTiles.contains(hotTiles)
                    }

                    fun canMoveTo(point: Point): Boolean {
                        return map.isInBoard(point) &&
                                (!cell.isObstacle || hasBooster(HotTiles.Drill))
                    }

                    fun canTeleportTo(point: Point): Boolean {
                        return gameState.teleportDestination.contains(point)
                    }

                    possible = when (action) {
                        Action.MoveUp -> canMoveTo(robotState.currentPosition.up())
                        Action.MoveDown -> canMoveTo(robotState.currentPosition.down())
                        Action.MoveLeft -> canMoveTo(robotState.currentPosition.left())
                        Action.MoveRight -> canMoveTo(robotState.currentPosition.right())
                        Action.DoNothing -> true
                        Action.TurnClockwise -> true
                        Action.TurnCounterClockwise -> true
                        is Action.AttachManipulator -> hasBooster(HotTiles.ExtraArm)
                        Action.AttachFastWheels -> hasBooster(HotTiles.FastWheels)
                        Action.StartDrill -> hasBooster(HotTiles.Drill)
                        Action.PlantTeleportResetPoint -> hasBooster(HotTiles.Teleporter)
                        is Action.TeleportBack -> canTeleportTo(action.targetResetPoint)
                        Action.CloneRobot -> hasBooster(HotTiles.CloneToken) &&
                                map.get(robotState.currentPosition).hasBooster(HotTiles.CloningLocation)
                    }
                }
                possible
            }
        }
    }
}
