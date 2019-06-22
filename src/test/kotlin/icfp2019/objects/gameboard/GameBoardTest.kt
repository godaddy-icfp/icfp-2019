package icfp2019.objects.gameboard

import icfp2019.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GameBoardTest {
  @Test
  fun testGameBoardGet() {
    val nodeMap = arrayOf(
      arrayOf(
        Node(Point(0, 0), false, null),
        Node(Point(0, 1), false, null),
        Node(Point(0, 2), true, null),
        Node(Point(0, 3), false, null)
      ),

      arrayOf(
        Node(Point(1, 0), true, null),
        Node(Point(1, 1), false, null),
        Node(Point(1, 2), true, null),
        Node(Point(1, 3), false, null)
      )

    )

    val problem = Problem(ProblemId(1), Size(2, 4), Point(0, 0), nodeMap)
    val gameBoard = GameBoard(problem)

    Assertions.assertTrue(gameBoard.cells.isNotEmpty())
  }
}
