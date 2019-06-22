package icfp2019

import icfp2019.analyzers.GraphAnalyzer
import icfp2019.core.Analyzer
import icfp2019.strategies.DFS
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.junit.jupiter.api.Test
import org.pcollections.TreePVector

class DFSTest {
    @Test
    fun testDFS() {
        val problem3Input =
            "(15,23),(16,23),(16,17),(15,17),(15,20),(12,20),(12,19),(10,19),(10,16),(12,16),(12,17),(13,17)," +
                    "(13,14),(14,14),(14,8),(16,8),(16,15),(18,15),(18,0),(27,0),(27,15),(22,15),(22,23),(19,23)," +
                    "(19,25),(24,25),(24,27),(18,27),(18,33),(16,33),(16,32),(11,32),(11,30),(16,30),(16,27),(15,27)," +
                    "(15,25),(13,25),(13,24),(12,24),(12,29),(9,29),(9,27),(8,27),(8,37),(2,37),(2,27),(3,27),(3,24)," +
                    "(9,24),(9,23),(0,23),(0,22),(9,22),(9,21),(13,21),(13,22),(15,22)#(0,22)#(20,7),(24,7),(24,5),(22,5)," +
                    "(22,6),(21,6),(21,5),(19,5),(19,4),(20,4),(20,3),(19,3),(19,2),(20,2),(20,1),(21,1),(21,4),(22,4),(22,3)," +
                    "(23,3),(23,4),(24,4),(24,3),(25,3),(25,7),(26,7),(26,13),(24,13),(24,14),(23,14),(23,13),(22,13),(22,14)," +
                    "(21,14),(21,13),(20,13)#X(16,25);L(19,19);F(4,30);F(17,21);B(4,31)"

        val problem = parseDesc(problem3Input)
        val gameBoard = GameBoard(problem.map, problem.size.x, problem.size.y)

        val robotState = RobotState(RobotId(0), Node(problem.startingPosition, false, true, null))
        val gameState = GameState(gameBoard, robotState)

        val graphAnalyzer = GraphAnalyzer.analyze(gameBoard).invoke(gameState)
        val moves = DFS().apply(graphAnalyzer, gameState, gameState.robotState.currentNode)
        val output = Output.encodeActions(moves)
        println(output)
    }
}