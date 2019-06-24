package icfp2019.strategies

import icfp2019.analyzers.BoardCellsGraphAnalyzer
import icfp2019.analyzers.ShortestPathUsingDijkstra
import icfp2019.core.Strategy
import icfp2019.model.*
import org.jgrapht.GraphPath
import org.jgrapht.graph.AsSubgraph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.traverse.BreadthFirstIterator
import org.jgrapht.traverse.GraphIterator

object BFSStrategy : Strategy {
    override fun compute(initialState: GameState): (robotId: RobotId, state: GameState) -> Action {
        val graphBuilder = BoardCellsGraphAnalyzer.analyze(initialState)
        return { robotId, gameState ->
            if (gameState.boardState().boostersAvailable(Booster.ExtraArm) > 0) {
                Action.AttachManipulator(gameState.robot(robotId).optimumManipulatorArmTarget())
            } else {
                val currentPoint = gameState.robotState.values.first().currentPosition
                val currentNode = graph.vertexSet().filter { currentPoint == it.point }[0]

                val unwrappedGraph =
                    AsSubgraph(
                        graph,
                        graph.vertexSet().filter { gameState.get(it.point).isWrapped.not() }.plus(currentNode).toSet()
                    )

                val bfsIterator: GraphIterator<Node, DefaultEdge> = BreadthFirstIterator(unwrappedGraph, currentNode)

                val neighbors = currentNode.point.neighbors()
                    .filter { gameState.isInBoard(it) }
                    .map { gameState.get(it) }
                if (neighbors.any {
                        it.isWrapped.not() && it.isObstacle.not()
                    }) {
                    bfsIterator.next() // move past currentNode
                    val neighbor = bfsIterator.next().point
                    currentPoint.actionToGetToNeighbor(neighbor)
                } else {
                    val analyze = ShortestPathUsingDijkstra.analyze(gameState)
                    val shortestPathAlgorithm = analyze(robotId, gameState)

                    val pathToClosestNode: GraphPath<Node, DefaultEdge> = unwrappedGraph.vertexSet()
                        .filter { it.point != currentNode.point }
                        .filter { it.isWrapped.not() }
                        .map { shortestPathAlgorithm.getPath(gameState.get(currentPoint), it) }
                        .minBy { it.length }!!

                    // pathToClosestNode.vertexList[0] is `currentNode`
                    val nextNode = pathToClosestNode.vertexList[1]
                    currentPoint.actionToGetToNeighbor(nextNode.point)
                }
            }
        }
    }
}
