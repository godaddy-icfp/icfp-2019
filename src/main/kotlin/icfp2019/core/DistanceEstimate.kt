package icfp2019.core

import icfp2019.analyzers.GraphAnalyzer
import icfp2019.analyzers.ShortestPathUsingDijkstra
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
import icfp2019.model.Point
import org.jgrapht.alg.connectivity.ConnectivityInspector
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree
import org.jgrapht.graph.AsSubgraph

data class DistanceEstimate(val distance: Int)

object ConservitiveDistanceAnalyzer : Analyzer<(position: Point) -> Pair<Int, Set<Node>>> {
    override fun analyze(map: GameBoard): (state: GameState) -> (position: Point) -> Pair<Int, Set<Node>> {
        val graphAnalyzer = GraphAnalyzer.analyze(map)
        val shortestPathAnalyzer = ShortestPathUsingDijkstra.analyze(map)
        return { state ->
            val graph = graphAnalyzer(state)
            val shortestPathAlgorithm = shortestPathAnalyzer(state)
            val unwrappedNodes = AsSubgraph(graph, graph.vertexSet().filter { it.isWrapped.not() }.toSet())
            val connectivityInspector = ConnectivityInspector(unwrappedNodes)
            val connectedGraphs = connectivityInspector.connectedSets();

            { point ->
                val node = map.get(point)
                val randomNodesFromEach: Set<Node> =
                    connectedGraphs.map { it.first() }
                        .plus(node)
                        .toSet()

                val nodes: Set<Node> = randomNodesFromEach.windowed(2, step = 1).map { (n1, n2) ->
                    shortestPathAlgorithm.getPath(n1, n2)
                }.flatMap { it.vertexList }.plus(connectedGraphs.flatten()).toSet()

                val connectedUnwrappedNodes = AsSubgraph(graph, nodes)

                val spanningTree = PrimMinimumSpanningTree(connectedUnwrappedNodes).spanningTree
                spanningTree.count() to spanningTree.edges.flatMap {
                    listOf(
                        graph.getEdgeSource(it),
                        graph.getEdgeTarget(it)
                    )
                }.toSet()
            }
        }
    }
}
