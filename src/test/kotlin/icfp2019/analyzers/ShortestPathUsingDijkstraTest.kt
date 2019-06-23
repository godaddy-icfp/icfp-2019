package icfp2019.analyzers

import icfp2019.model.GameState
import icfp2019.model.Point
import icfp2019.model.RobotId
import icfp2019.toProblem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ShortestPathUsingDijkstraTest {

    @Test
    fun testShortestDistanceToPoint() {
        val map3x2 = """
            ...
            ...
        """.toProblem()

        val gameState = GameState(map3x2)

        val graph = ShortestPathUsingDijkstra
            .analyze(gameState)(RobotId.first, gameState)
            .getPath(
                gameState.get(Point.origin()),
                gameState.get(Point(2, 1))
            )

        Assertions.assertEquals(3, graph.length)
    }
}
