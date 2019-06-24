package icfp2019.analyzers

import icfp2019.core.applyAction
import icfp2019.model.Action
import icfp2019.model.GameState
import icfp2019.model.RobotId
import icfp2019.strategies.BFSStrategy
import icfp2019.toProblem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class BFSAnalyzerTest {
    @Test
    fun testBfsAnalyzerTest() {
        val map3x2 = """
            X.X.XX
            X@....
        """.toProblem()

        val startState = GameState(map3x2)
        val analyzer = BFSAnalyzer.analyze(startState)
        println(analyzer)
        val startingWalkState: Pair<GameState, List<Action>> = startState to listOf()
        val result: Pair<GameState, List<Action>> = generateSequence(startingWalkState) { (state, actions) ->
            if (state.isGameComplete()) null
            else {
                val action = analyzer.invoke(RobotId.first, state)
                val newState = applyAction(state, RobotId.first, action.first())
                newState to actions.plus(action)
            }
        }.last()
        result.second.forEach(System.out::println)
    }

    @Test
    fun testBfsAnalyzerTest2() {
        val map3x2 = """
            .X.XX
            @....
            .XXXX
        """.toProblem()
        val startState = GameState(map3x2)
        val analyzer = BFSAnalyzer.analyze(startState)
        println(analyzer)
        val startingWalkState: Pair<GameState, List<Action>> = startState to listOf()
        val result: Pair<GameState, List<Action>> = generateSequence(startingWalkState) { (state, actions) ->
            if (state.isGameComplete()) null
            else {
                val action = analyzer.invoke(RobotId.first, state)
                val newState = applyAction(state, RobotId.first, action.first())
                newState to actions.plus(action)
            }
        }.last()
        result.second.forEach(System.out::println)
    }

    @Test
    fun `add rotate`() {
//        val givenMap = """
//            ....
//            .X.X
//            X@.X
//            X...
//            ....
//            ....
//        """.toProblem()

        val givenMap = """
            ...
            .X.
            X@.
            X..
        """.toProblem()
//
//        val givenMap = """
//            ...
//            ...
//            .@.
//        """.toProblem()
        val startState = GameState(givenMap)

        val analyzer = BFSAnalyzer.analyze(startState)
        println(analyzer)
        val startingWalkState: Pair<GameState, List<Action>> = startState to listOf()
        val result: Pair<GameState, List<Action>> = generateSequence(startingWalkState) { (state, actions) ->
            if (state.isGameComplete()) null
            else {
                val action = analyzer.invoke(RobotId.first, state)
                val newState = applyAction(state, RobotId.first, action.first())
                newState to actions.plus(action)
                val newState2 = applyAction(state, RobotId.first, action.last())
                newState2 to actions.plus(action)
            }
        }.last()
        result.second.forEach(System.out::println)
        // expected: up, up
    }

    @Test
    fun `test getNumWrappedOnArm`() {

        val givenMap = """
            ...
            @..
            X.X
        """.toProblem()
        val startState = GameState(givenMap)
        val robotId = RobotId.first

        val numWrappedOnArm = BFSStrategy.getNumWrappedOnArm(startState, robotId)
        Assertions.assertEquals(3, numWrappedOnArm)

        val rotateClockwiseState = applyAction(startState, robotId, Action.TurnClockwise)
        val numWrappedOnArm2 = BFSStrategy.getNumWrappedOnArm(rotateClockwiseState, robotId)
        Assertions.assertEquals(2, numWrappedOnArm2)
    }
}
