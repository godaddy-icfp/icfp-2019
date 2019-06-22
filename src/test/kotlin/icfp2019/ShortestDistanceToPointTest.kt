package icfp2019

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import org.jgrapht.graph.SimpleGraph
import org.junit.jupiter.api.Test


class ShortestDistanceToPointTest {

    @Test
    fun testShortestDistanceToPoint() {

        val currentNode = Node(Point(0,0),true,null)

        val node01 = Node(Point(0,1),true,null)
        val node02 = Node(Point(0,2),true,null)
        val node03 = Node(Point(0,3),true,null)
        val node10 = Node(Point(1,0),true,null)
        val node11 = Node(Point(1,1),true,null)
        val node12 = Node(Point(1,2),true,null)
        val node13 = Node(Point(1,3),true,null)
        val node20 = Node(Point(2,0),true,null)
        val node21 = Node(Point(2,1),true,null)
        val node22 = Node(Point(2,2),true,null)
        val node23 = Node(Point(2,3),true,null)

        val undirectedGraph = SimpleGraph<Node, DefaultEdge>(DefaultEdge::class.java)
        undirectedGraph.addVertex(node01)
        undirectedGraph.addVertex(node02)
        undirectedGraph.addVertex(node03)
        undirectedGraph.addVertex(node10)
        undirectedGraph.addVertex(node11)
        undirectedGraph.addVertex(node12)
        undirectedGraph.addVertex(node13)
        undirectedGraph.addVertex(node20)
        undirectedGraph.addVertex(node21)
        undirectedGraph.addVertex(node22)
        undirectedGraph.addVertex(node23)
        undirectedGraph.addVertex(currentNode)


        undirectedGraph.addEdge("a", "b")
        undirectedGraph.addEdge("b","d")
        undirectedGraph.addEdge("b","e")
        undirectedGraph.addEdge("d","g")
        undirectedGraph.addEdge("e","g")
        undirectedGraph.addEdge("e","f")
        undirectedGraph.addEdge("a","f")
        undirectedGraph.addEdge("f","g")
        undirectedGraph.addEdge("g","h")
        undirectedGraph.addEdge("h","i")

        val shortestPathUndirectedGraph = DijkstraShortestPath(undirectedGraph)
        val ipaths1 = shortestPathUndirectedGraph.getPath("i", "d")
        println(ipaths1)
        println()






    }
}