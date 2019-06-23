package icfp2019.strategies

import icfp2019.analyzers.GraphAnalyzer
import icfp2019.core.DistanceEstimate
import icfp2019.core.Proposal
import icfp2019.core.Strategy
import icfp2019.model.*
import org.jgrapht.Graph
import org.jgrapht.graph.AsSubgraph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.traverse.GraphIterator

// Move to an open space and push moves onto a stack, if no moves available then backtrack using the stack
object DFSStrategy : Strategy {
    override fun compute(map: GameBoard): (state: GameState) -> Proposal {
        return { gameState ->

            val undirectedGraph: Graph<Node, DefaultEdge> = GraphAnalyzer.analyze(map).invoke(gameState)
            val currPoint = gameState.robotState.values.first().currentPosition
            val currNode = gameState.get(currPoint)
            // Sub graph with unwrapped nodes
            val unwrappedNodes = AsSubgraph(undirectedGraph, undirectedGraph.vertexSet().filter { it.isWrapped.not() }.plus(currNode).toSet())

            // We need to tell the DFS the starting point, otherwise it always (0,0) regardless where the robot is
            var stack = DepthFirstIterator(unwrappedNodes, currNode).stack
            println(stack)

            val it: GraphIterator<Node, DefaultEdge> = DepthFirstIterator(unwrappedNodes, currNode)
            it.next() // ignore the first node since it always a starting point where the robot standing

            if (it.hasNext()) {
                currNode.isWrapped = true
                val nextNeighbor  = it.next()
                Proposal(DistanceEstimate(0), currPoint.actionToGetToNeighbor(nextNeighbor.point))
            } else {
                // This only happens if the graph has only one node
                Proposal(DistanceEstimate(0), Action.DoNothing)
            }
        }
    }
}
