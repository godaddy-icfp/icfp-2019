package icfp2019.core

import icfp2019.model.Action
import icfp2019.model.GameState
import icfp2019.model.Point
import icfp2019.model.RobotId
import icfp2019.parseTestMap
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

    @Test
    fun verifyWrapping() {

        val testMap = """
        ...XX
        .....
        @..XX
    """.trimIndent()
        val problem = parseTestMap(testMap)
        val gameState = GameState.gameStateOf(problem)

        val actions = listOf(
            Action.Initialize, Action.MoveUp, Action.MoveUp,
            Action.MoveRight, Action.MoveDown, Action.MoveDown,
            Action.MoveRight, Action.MoveUp, Action.MoveUp,
            Action.MoveDown, Action.MoveRight
        )
        val expectedMap = """
        wwwXX
        wwww.
        wwwXX
    """.trimIndent()

        actions.applyTo(gameState).let {
            Assertions.assertEquals(parseTestMap(expectedMap).map, it.cells)
        }
    }

    private fun List<Action>.applyTo(gameState: GameState): GameState {
        return this.fold(gameState) { state, action ->
            applyAction(state, RobotId.first, action)
        }
    }
}
