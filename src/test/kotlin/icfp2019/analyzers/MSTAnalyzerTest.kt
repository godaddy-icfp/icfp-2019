package icfp2019.analyzers

import icfp2019.GameBoard
import icfp2019.GameState
import icfp2019.Node
import icfp2019.Point
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

class MSTAnalyzerTest {

    @Test
    fun testMinimumSpanningTree() {
        var gameBoard = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false),
                            Node(Point(0, 1), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(1, 0), false),
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

        val spanningTree = MSTAnalyzer
            .analyze(gameBoard)(GameState.empty(Point(2, 1)))

        val count = spanningTree.edges.size
        Assertions.assertEquals(count, 5)
    }
}