package icfp2019.analyzers

<<<<<<< HEAD
import icfp2019.GameBoard
import icfp2019.GameState
import icfp2019.Node
import icfp2019.Point
=======
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
import icfp2019.model.Point
>>>>>>> fd3087867b2c9f9436fe332501f237640197adfc
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

class ShortestPathsUsingFloydWarshallTest {

    @Test
    fun testShortestDistanceToPoint() {
        var gameBoard = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false),
                            Node(Point(0, 1), false),
                            Node(Point(0, 2), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(1, 0), false),
                            Node(Point(1, 1), false),
                            Node(Point(1, 2), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(2, 0), false),
                            Node(Point(2, 1), false),
                            Node(Point(2, 2), false)
                        )
                    )
                )
            ), 3, 3
        )

        val graph = ShortestPathUsingFlyodWarshall
            .analyze(gameBoard)(GameState.empty(Point(0, 0)))
            .getPath(
                Node(Point(0, 0), false),
                Node(Point(2, 1), false)
            )

        Assertions.assertEquals(graph.vertexList, listOf(
            Node(Point(0, 0), false),
            Node(Point(1, 0), false),
            Node(Point(2, 0), false),
            Node(Point(2, 1), false)
        ))
    }
}