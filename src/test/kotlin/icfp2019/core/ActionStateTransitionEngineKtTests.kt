package icfp2019.core

import icfp2019.model.Action
import icfp2019.model.GameState
import icfp2019.model.Point
import icfp2019.model.RobotId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ActionStateTransitionEngineKtTests {

    @Test
    fun testApplyMove() {
        val startingPosition = Point.origin()
        val gameStateOf = GameState.gameStateOf(startingPosition)
        val upRightState = applyAction(gameStateOf, RobotId.first, Action.MoveUp).let {
            applyAction(it, RobotId.first, Action.MoveRight)
        }

        Assertions.assertEquals(
            startingPosition.up().right(),
            upRightState.robotState.getValue(RobotId.first).currentPosition
        )

        val backToOrigin = applyAction(upRightState, RobotId.first, Action.MoveDown).let {
            applyAction(it, RobotId.first, Action.MoveLeft)
        }

        Assertions.assertEquals(
            startingPosition,
            backToOrigin.robotState.getValue(RobotId.first).currentPosition
        )
    }
}
