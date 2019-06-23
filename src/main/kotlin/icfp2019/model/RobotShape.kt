package icfp2019.model

import icfp2019.analyzers.MoveAnalyzer
import umontreal.ssj.util.BitMatrix
import java.lang.IllegalStateException

class RobotShape(val bitMatrix: BitMatrix,
                 val gameBoard: GameBoard,
                 val gameState: GameState,
                 private var currentArmPosition1: Point = Point(),
                 private var currentArmPosition2: Point = Point(),
                 private var currentArmPosition3: Point = Point(),
                 private var currentArmPosition4Extra: Point = Point()) {

    // Game State and board for now till they're merged -- Initial State
    init {
        gameState.robotState.forEach { _, robotStateValue ->
            MoveAnalyzer.analyze(gameBoard)(gameState)(robotStateValue.robotId, Action.DoNothing)
                .apply {
                    currentArmPosition1 = Point(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y)
                    currentArmPosition2 = Point(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y + 1)
                    currentArmPosition3 = Point(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y - 1)
                }.run {
                    with(bitMatrix) {
                        setBool(robotStateValue.currentPosition.x, robotStateValue.currentPosition.y, true)
                        setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y, true)
                        setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y + 1, true)
                        setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y - 1, true)
                    }
                }
        }
    }

    fun paintActionPositions(booster: Boosters) {
       when(booster) {
            Boosters.ExtraArm -> {
                gameState.robotState.forEach { _, robotStateValue ->
                    MoveAnalyzer.analyze(gameBoard)(gameState)(robotStateValue.robotId, Action.DoNothing)
                        .takeIf { true }
                        ?:apply {
                            // Picking up at the current arm point so just use that point?
                            this.currentArmPosition4Extra = Point(robotStateValue.currentPosition.x, robotStateValue.currentPosition.y)
                        }.run {
                            with(bitMatrix) {
                                // Paint all four arms
                                setBool(robotStateValue.currentPosition.x, robotStateValue.currentPosition.y, true)
                                setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y, true)
                                setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y + 1, true)
                                setBool(robotStateValue.currentPosition.x + 1, robotStateValue.currentPosition.y - 1, true)
                            }
                        }
                }
            }
            Boosters.Drill -> {
                gameState.robotState.forEach { _, robotStateValue ->
                    MoveAnalyzer.analyze(gameBoard)(gameState)(robotStateValue.robotId, Action.DoNothing)
                        .takeIf { true }
                        .run {
                            with(bitMatrix) {
                                // Paint just drill location
                                setBool(robotStateValue.currentPosition.x, robotStateValue.currentPosition.y, true)
                            }
                        }
                }
            }
            Boosters.Drill -> {}
            else -> throw IllegalStateException("Invalid Booster ${booster}")
        }
    }

}
