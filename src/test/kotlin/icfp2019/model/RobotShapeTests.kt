package icfp2019.model

import icfp2019.parseTestMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import umontreal.ssj.util.BitMatrix
import java.lang.IllegalStateException
import java.util.*

class RobotShapeTests {
    val map = """
                XXXXXXXXXXXXXXXXXXXXXXXXXX
                XX..XXXXXXXXXXXXXX..XXXXXX
                XX..XXXXXXXXXXXXXX..XXXXXX
                XX..XXXXXXXXXXXXXX..XXXXXX
                XX..XXXXXXXXXXXXXX..XXXXXX
                XX...XXXXXXXXXXXX...XXXXXX
                XX...XXXXXXXXXXXX...XXXXXX
                XX...XXXXXX.XXXXX...XXXXXX
                XX...XXXXX..XXXXX...XXXXXX
                XX....XXXX..XXXX....XXXXXX
                XX.....XX....XX.....XXXXXX
                XXwwwwwwwwwwwwwwwwwwwwXXXX
                XXwwwwwwwwwwwwwwwwwwwwXXXX
                XXwwwwwwwwwwwwwwwwwwwwXXXX
                XXwwwwwwwwwwwwwwwwwwwwXXXX
                XXwwwwwwwwwwwwwwwwwwwwXXXX
                XXwwwwwwww@wwwwwwwwwwwXXXX
                XXXXXXXXXXXXXXXXXXXXXXXXXX"""

    @Test
    fun testRobotExtraArm() {
        val problem = parseTestMap(map)
        val matrix = BitMatrix(problem.size.x, problem.size.y)
        val robotShape = RobotShape(matrix, GameBoard.gameBoardOf(problem), GameState.gameStateOf(Point(1, 1)))
        Assertions.assertTrue(robotShape.bitMatrix.getBool(1, 1))
    }

    @Test
    fun testRobotPaintActionPositions() {
        val problem = parseTestMap(map)
        val matrix = BitMatrix(problem.size.x, problem.size.y)
        val robotShape = RobotShape(matrix, GameBoard.gameBoardOf(problem), GameState.gameStateOf(Point(1, 1)))
        robotShape.paintActionPositions(Boosters.ExtraArm)
        Assertions.assertTrue(robotShape.bitMatrix.getBool(1, 1))
    }
}