package icfp2019.parser

import icfp2019.*
import icfp2019.Boosters
import icfp2019.Problem
import icfp2019.ProblemDescription
import mu.KLogging

enum class Boosters {
    B, F, L, X
}

data class Point(val x: Int, val y: Int)
data class Node(val point: Point, val isObstacle: Boolean, val booster: Boosters?)
data class ProblemId(val id: Int)
data class ProblemDescription(val problemId: ProblemId, val line: String)
data class Problem(val problemId: ProblemId, val startingPosition: Point, val map: Array<Array<Node>>)

/*
Task:
 1. Open Zip file
 2. parse a problem at a time: prob_NNN.desc
 3. solve problem
 4. encode solution
 5. output to file prob_NNN.sol (use checker to validate?) https://icfpcontest2019.github.io/solution_checker/
 6. add solution to another zip (script/program)
 */
class Parser {
    companion object: KLogging()

    fun parseDesc(problem: ProblemDescription): Problem {
        logger.debug("parseDesc(): $problem")
        val (mapEdges, startPosition, obstacles, boosters) = problem.line.split('#')
        val startPoint = parsePoint(startPosition)
        val verticies = parseEdges(mapEdges)
        val obstacleEdges = parseEdges(obstacles)
        val parsedBosters = parseBoosters(boosters)

        val maxY = verticies.maxBy { it.y }?.y ?: throw RuntimeException()
        val maxX = verticies.maxBy { it.x }?.x ?: throw RuntimeException()

        val grid = (0..maxX).map { x ->
            (0..maxY).map { y ->
                Node(icfp2019.Point(x, y), isObstacle = true, booster = null)
            }.toTypedArray()
        }.toTypedArray()

        verticies.forEach {
            grid[it.x][it.y] = grid[it.x][it.y].copy(isObstacle = false)
        }

        // Read lines
        /*
        1. Read lines
        2. Parse map
        Grammar:
          x,y: Nat
                    point ::= (x,y)
                      map ::= repSep(point,”,”)
              BoosterCode ::= B|F|L|X
          boosterLocation ::= BoosterCode point
                obstacles ::= repSep(map,”; ”)
                 boosters ::= repSep(boosterLocation,”; ”)
                     task ::= map # point # obstacles # boosters
         */
        return Problem(problem.problemId, startPoint, arrayOf())
    }
}

