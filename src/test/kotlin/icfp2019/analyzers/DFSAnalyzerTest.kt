package icfp2019.analyzers

import icfp2019.model.*
import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

class DFSAnalyzerTest {

    @Test
    fun testTwoNodesStart00moveTo01() {
        var gameBoard = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(1, 0), false)
                        )
                    )
                )
            ), 2, 1
        )

        val robotState = RobotState(RobotId.first, Point(0, 0), Orientation.Up, 0)
        val gameState = GameState(
            gameBoard.cells,
            MapSize(gameBoard.width, gameBoard.height),
            mapOf(RobotId.first to robotState),
            listOf(),
            listOf())
        val analyzer = DFSAnalyzer.analyze(gameBoard)

        val firstMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveRight, firstMove)

        // After Robot moved to the right, we need to update its curr position
        gameState.updateRobotPosition(RobotId.first, Point(1, 0))

        val secondMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveLeft, secondMove)
    }

    @Test
    fun testTwoNodesStart01moveTo00() {
        var gameBoard = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false)
                        )
                    ),
                    TreePVector.from(
                        listOf(
                            Node(Point(1, 0), false)
                        )
                    )
                )
            ), 2, 1
        )

        val robotState = RobotState(RobotId.first, Point(1, 0), Orientation.Up, 0)
        val gameState = GameState(
            gameBoard.cells,
            MapSize(gameBoard.width, gameBoard.height),
            mapOf(RobotId.first to robotState),
            listOf(),
            listOf())
        val analyzer = DFSAnalyzer.analyze(gameBoard)

        val firstMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveLeft, firstMove)

        // After Robot moved to the left, we need to update its curr position
        gameState.updateRobotPosition(RobotId.first, Point(0, 0))

        val secondMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveRight, secondMove)
    }

    @Test
    fun testOneNodesStart00doNothing() {
        var gameBoard = GameBoard(
            TreePVector.from(
                listOf(
                    TreePVector.from(
                        listOf(
                            Node(Point(0, 0), false)
                        )
                    )
                )
            ), 1, 1
        )

        val robotState = RobotState(RobotId.first, Point(0, 0), Orientation.Up, 0)
        val gameState = GameState(
            gameBoard.cells,
            MapSize(gameBoard.width, gameBoard.height),
            mapOf(RobotId.first to robotState),
            listOf(),
            listOf())
        val analyzer = DFSAnalyzer.analyze(gameBoard)

        val firstMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.DoNothing, firstMove)

        // After Robot moved to the right, we need to update its curr position
        gameState.updateRobotPosition(RobotId.first, Point(0, 0))

        val secondMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.DoNothing, secondMove)
    }

    @Test
    fun testFourNodesStart00dfsMoveToAllNodes() {
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
                    )
                )
            ), 2, 2
        )

        val robotState = RobotState(RobotId.first, Point(0, 0), Orientation.Up, 0)
        val gameState = GameState(
            gameBoard.cells,
            MapSize(gameBoard.width, gameBoard.height),
            mapOf(RobotId.first to robotState),
            listOf(),
            listOf())
        val analyzer = DFSAnalyzer.analyze(gameBoard)

        val firstMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveRight, firstMove)

        gameState.updateRobotPosition(RobotId.first, Point(1, 0))

        val secondMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveUp, secondMove)

        gameState.updateRobotPosition(RobotId.first, Point(1, 1))

        val thirdMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.MoveLeft, thirdMove)

        gameState.updateRobotPosition(RobotId.first, Point(0, 1))

        //backtracking to its initial position
        val fourMove = analyzer.invoke(gameState).iterator().next()
        Assertions.assertEquals(Action.DoNothing, fourMove)
    }
}
