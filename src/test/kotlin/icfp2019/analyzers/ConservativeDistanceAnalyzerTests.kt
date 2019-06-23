package icfp2019.analyzers

import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Point
import icfp2019.parseTestMap
import icfp2019.printBoard
import org.junit.jupiter.api.Test

internal class ConservativeDistanceAnalyzerTests {

    @Test
    fun analyze() {
        val problem = parseTestMap(map)
        printBoard(problem)
        val analyzer = ConservativeDistanceAnalyzer.analyze(
            GameBoard.gameBoardOf(
                problem
            )
        ).invoke(GameState.empty(Point.origin()))
        val result = analyzer(problem.startingPosition)
        println(result.estimate)
        printBoard(problem, result.pathNodes)
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
                XXXXXXXXXXXXXXXXXXXXXXXXXX"""
}
