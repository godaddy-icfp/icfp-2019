package icfp2019

import icfp2019.analyzers.GraphAnalyzer
import icfp2019.core.Analyzer
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge

object ShortestPathUsingDijkstra : Analyzer<ShortestPathAlgorithm<Node, DefaultEdge>> {
    override fun analyze(map: GameBoard): (state: GameState) -> ShortestPathAlgorithm<Node, DefaultEdge> {
        val completeGraph = GraphAnalyzer.analyze(map)
        return { graphState ->
            val graph = completeGraph(graphState)
            DijkstraShortestPath(graph)
        }
    }
}