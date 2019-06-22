package icfp2019

import icfp2019.analyzers.GetNumberOfWrappedOrNot
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
import icfp2019.model.Point
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

class GetNumberOfWrappedOrNotTests {
    @Test
    fun testSimple() {
        var g = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false, true),
                            Node(Point(0, 1), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(1, 0), false, true),
                            Node(Point(1, 1), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(2, 0), false),
                            Node(Point(2, 1), false)
                        )
                    )
                )
            ), 3, 2
        )

        val columns = g.cells
        Assertions.assertEquals(3, columns.size)
        Assertions.assertEquals(2, columns[0].size)
        Assertions.assertEquals(2, columns[1].size)
        Assertions.assertEquals(2, columns[2].size)

        val results = GetNumberOfWrappedOrNot.analyze(g)(GameState.empty(Point(0, 0)))
        Assertions.assertEquals(2, results.wrapped)
        Assertions.assertEquals(4, results.unwrapped)
    }
}
