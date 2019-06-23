package icfp2019.strategies

import icfp2019.analyzers.GraphAnalyzer
import icfp2019.analyzers.MoveListAnalyzer
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
            val it: GraphIterator<Node, DefaultEdge> = DepthFirstIterator(unwrappedNodes, currNode)
            it.next() // ignore the first node since it always a starting point which where the robot standing

            if (it.hasNext()) {
                println("Backing tracing")
                val nextNode  = it.next()
                val moves2: List<Action> =
                    when (!nextNode.isWrapped) {
                        true -> MoveListAnalyzer.analyze(map)
                            .invoke(gameState)
                            .invoke(
                                (gameState.robotState[RobotId.first] ?: error("unable to find first robot")).robotId
                            )
                        false -> listOf()
                    }
                Proposal(DistanceEstimate(0), pickMove(moves2))
            } else {
                // This only happend if the graph has only one node
                Proposal(DistanceEstimate(0), Action.DoNothing)
            }
        }

    }

    // A "heuristic" for picking movements random shuffle and get the first
    private fun pickMove(moves: List<Action>): Action {
        return moves.filter {
            it is Action.MoveDown ||
                    it is Action.MoveUp ||
                    it is Action.MoveLeft ||
                    it is Action.MoveRight
        }.shuffled().first()
    }
}
