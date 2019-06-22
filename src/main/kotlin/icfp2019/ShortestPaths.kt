package icfp2019

import icfp2019.core.Analyzer
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.graph.DefaultEdge

object ShortestPaths : Analyzer<ShortestPathAlgorithm<Node, DefaultEdge>> {
    override fun analyze(map: GameBoard): (state: GameState) -> ShortestPathAlgorithm<Node, DefaultEdge> {

        return {it ->
        }
    }

}