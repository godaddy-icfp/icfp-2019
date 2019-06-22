package icfp2019

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParserTests {
    @Test
    fun testParser() {
        val result: List<Point> = parseEdges("(1,2),(3,4)")
        Assertions.assertEquals(result, listOf(Point(1, 2), Point(3, 4)))
    }

    @Test
    fun simpleBoard() {
        val problem1Input = "(0,0),(6,0),(6,1),(8,1),(8,2),(6,2),(6,3),(0,3)#(0,0)##"
        val problemDescription = ProblemParseInput(ProblemId(1), problem1Input)

        val problem = parseDesc(problemDescription)
        /* 0 1 2 3 4 5 6 7
         2 . . . . . . X X
         1 . . . . . . . .
         0 . . . . . . X X
         */

        printBoard(problem)
    }

    @Test
    fun testFullFile() {
        val problem3Input =
            "(15,23),(16,23),(16,17),(15,17),(15,20),(12,20),(12,19),(10,19),(10,16),(12,16),(12,17),(13,17)," +
                    "(13,14),(14,14),(14,8),(16,8),(16,15),(18,15),(18,0),(27,0),(27,15),(22,15),(22,23),(19,23)," +
                    "(19,25),(24,25),(24,27),(18,27),(18,33),(16,33),(16,32),(11,32),(11,30),(16,30),(16,27),(15,27)," +
                    "(15,25),(13,25),(13,24),(12,24),(12,29),(9,29),(9,27),(8,27),(8,37),(2,37),(2,27),(3,27),(3,24)," +
                    "(9,24),(9,23),(0,23),(0,22),(9,22),(9,21),(13,21),(13,22),(15,22)#(0,22)#(20,7),(24,7),(24,5),(22,5)," +
                    "(22,6),(21,6),(21,5),(19,5),(19,4),(20,4),(20,3),(19,3),(19,2),(20,2),(20,1),(21,1),(21,4),(22,4),(22,3)," +
                    "(23,3),(23,4),(24,4),(24,3),(25,3),(25,7),(26,7),(26,13),(24,13),(24,14),(23,14),(23,13),(22,13),(22,14)," +
                    "(21,14),(21,13),(20,13)#X(16,25);L(19,19);F(4,30);F(17,21);B(4,31)"

        val problem = ProblemParseInput(ProblemId(3), problem3Input)
        val p = parseDesc(problem)

        p.map[4].forEach { x ->
            print(
                if (x.isObstacle) {
                    'X'
                } else {
                    '.'
                }
            )
        }

        println()
        println("===================")

        printBoard(p)
    }

    private fun printBoard(p: Problem) {
        println("${p.size}")
        for (y in (p.size.y - 1) downTo 0) {
            for (x in 0 until p.size.x) {
                if (p.startingPosition == Point(x, y)) {
                    print('s')
                    continue
                }
                val node = p.map[x][y]
                val char = if (node.isObstacle) {
                    'X'
                } else {
                    if (node.booster != null) {
                        'o'
                    } else {
                        '.'
                    }
                }
                print(char)
                print(' ')
            }
            println()
        }
    }

    @Test
    fun testBoosterParser() {
        val boosters = "X(16,25);L(19,19);F(4,30);F(17,21);B(4,31)"
        val actual = parseBoosters(boosters)
        val expected = listOf(
            Pair(Booster.CloningLocation, Point(16, 25)),
            Pair(Booster.Drill, Point(19, 19)),
            Pair(Booster.FastWheels, Point(4, 30)),
            Pair(Booster.FastWheels, Point(17, 21)),
            Pair(Booster.ExtraArm, Point(4, 31))
        )
        Assertions.assertEquals(actual, expected)
    }
}
