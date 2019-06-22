package icfp2019

import org.jgrapht.GraphPath
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

/**
 * Given a node and a set of node that could be a Booster or a unwrapped point in the node, we determine
 * shortest path to reach to the set of points and the direction/ orientation that it has to be in to
 * reach there.
 *
 */
fun getShortestDistanceFromPoints(
    currentNode: Node,
    possibleNodeSets : Set<Node>,
    simpleGraph: SimpleGraph<Node, DefaultEdge>): Array<GraphPath<Node,DefaultEdge>> {

    val shortestPathUndirectedGraph = DijkstraShortestPath(simpleGraph)
    return possibleNodeSets.map {
        shortestPathUndirectedGraph.getPath(currentNode, it)
    }.toTypedArray()
}



