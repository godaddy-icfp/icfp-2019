package icfp2019

import icfp2019.core.DistanceEstimate
import icfp2019.core.Proposal
import icfp2019.core.Strategy
import icfp2019.model.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.lang.IllegalStateException
import java.util.*

internal class BrainKtTest {
    class TestStrategy(vararg actions: Action) : Strategy {
        private val queue = ArrayDeque(actions.toList())

        override fun compute(map: GameBoard): (state: GameState) -> Proposal {
            return {
                if (queue.isEmpty()) {
                    Proposal(DistanceEstimate(0), Action.DoNothing)
                } else {
                    Proposal(DistanceEstimate(0), queue.pop())
                }
            }
        }
    }

    @Test
    fun brainStep() {
        val problem = parseTestMap(init)
        val solution = parseTestMap(fini)
        printBoard(problem)
        var state = GameState.gameStateOf(problem)
        val strategies = listOf(
            TestStrategy(Action.MoveDown, Action.DoNothing, Action.MoveDown),
            TestStrategy(Action.DoNothing, Action.MoveRight))
        for (i in 0..3) {
            val (result, actions) = brainStep(
                state,
                strategies,
                { true },
                1
            )

            state = result

            println(actions)
            val problem0 = problem.copy(map = result.cells)
            printBoard(problem0)
        }

        Assertions.assertEquals(solution.map, state.cells)
    }

    private val init =
        """
        @..
        ...
        ...
        """

    private val fini =
        """
        @..
        www
        ..w
        """
}
