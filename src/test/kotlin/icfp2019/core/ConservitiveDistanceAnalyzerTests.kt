package icfp2019.core

import com.google.common.base.CharMatcher
import com.google.common.base.Splitter
import icfp2019.model.*
import icfp2019.printBoard
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

internal class ConservitiveDistanceAnalyzerTests {

    @Test
    fun analyze() {
        val problem = parseTestMap(map)
        printBoard(problem)
        val analyzer = ConservitiveDistanceAnalyzer.analyze(
            GameBoard.gameBoardOf(
                problem
            )
        ).invoke(GameState.empty(Point.origin()))
        val result = analyzer(problem.startingPosition)
        println(result.first)
        printBoard(problem, result.second)
    }

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
            XXXXXXXXXXXXXXXXXXXXXXXXXX
            """

    private fun parseTestMap(map: String): Problem {
        val lines = Splitter.on(CharMatcher.breakingWhitespace()).splitToList(map.trim()).reversed()
            .map { CharMatcher.whitespace().removeFrom(it) }
        val height = lines.size
        val width = lines[0].length
        if (lines.any { it.length != width }) throw IllegalArgumentException()
        val startPoint =
            (0 until width).map { x ->
                (0 until height).map { y ->
                    if (lines[y][x] == '@') Point(x, y)
                    else null
                }
            }.flatten().find { it != null } ?: Point.origin()
        return Problem(MapSize(width, height), startPoint, TreePVector.from((0 until width).map { y ->
            TreePVector.from((0 until height).map { x ->
                val point = Point(x, y)
                when (lines[x][y]) {
                    'X' -> Node(point, isObstacle = true)
                    'w' -> Node(point, isObstacle = false, isWrapped = true)
                    '.' -> Node(point, isObstacle = false)
                    '@' -> Node(point, isObstacle = false)
                    else -> throw IllegalArgumentException("Unknown Char '${lines[x][y]}'")
                }
            })
        }))
    }
}
