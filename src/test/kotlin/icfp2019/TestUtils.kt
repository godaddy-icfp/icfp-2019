package icfp2019

import icfp2019.model.Node
import icfp2019.model.Point
import icfp2019.model.Problem
import java.nio.file.Paths

fun loadProblem(problemNumber: Int): String {
    val path = Paths.get("problems/prob-${problemNumber.toString().padStart(3, padChar = '0')}.desc").toAbsolutePath()
    return path.toFile().readText()
}

fun boardString(p: Problem, path: Set<Node> = setOf()): String {
    val lines = mutableListOf<String>()
    for (y in (p.size.y - 1) downTo 0) {

        val row = (0 until p.size.x).map { x ->
            val node = p.map[x][y]
            when {
                p.startingPosition == Point(x, y) -> '@'
                node.isObstacle -> 'X'
                node in path -> '|'
                node.isWrapped -> 'w'
                node.booster != null -> 'o'
                else -> '.'
            }
        }.joinToString(separator = " ")
        lines.add(row)
    }
    return lines.joinToString(separator = "\n")
}

fun printBoard(p: Problem, path: Set<Node> = setOf()) {
    println("${p.size}")
    print(boardString(p, path))
    println()
}
