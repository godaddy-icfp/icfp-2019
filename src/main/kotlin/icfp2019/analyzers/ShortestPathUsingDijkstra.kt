package icfp2019.analyzers

<<<<<<< HEAD
import icfp2019.GameBoard
import icfp2019.GameState
import icfp2019.Node
import icfp2019.core.Analyzer
=======
import icfp2019.core.Analyzer
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
>>>>>>> fd3087867b2c9f9436fe332501f237640197adfc
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