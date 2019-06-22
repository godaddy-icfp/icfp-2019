package icfp2019

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ShortestDistanceToPointTest {

    @Test
    fun testShortestDistanceToPoint() {

        val currentNode = Node(Point(0,0),false,null)

        val node01 = Node(Point(0,1),false,null)
        val node02 = Node(Point(0,2),false,null)
        val node03 = Node(Point(0,3),false,null)
        val node10 = Node(Point(1,0),false,null)
        val node11 = Node(Point(1,1),false,null)
        val node12 = Node(Point(1,2),false,null)
        val node13 = Node(Point(1,3),false,null)
        val node20 = Node(Point(2,0),false,null)
        val node21 = Node(Point(2,1),false,null)
        val node22 = Node(Point(2,2),false,null)
        val node23 = Node(Point(2,3),false,null)

        val setOfTargetNodes = setOf(
            node21
        )

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


        undirectedGraph.addEdge(currentNode,node01)
        undirectedGraph.addEdge(node01,node02)
        undirectedGraph.addEdge(node02,node03)
        undirectedGraph.addEdge(currentNode,node10)
        undirectedGraph.addEdge(node10,node20)
        undirectedGraph.addEdge(node01,node11)
        undirectedGraph.addEdge(node11,node21)
        undirectedGraph.addEdge(node02,node12)
        undirectedGraph.addEdge(node12,node22)
        undirectedGraph.addEdge(node03,node13)
        undirectedGraph.addEdge(node13,node23)
        undirectedGraph.addEdge(node10,node11)
        undirectedGraph.addEdge(node11,node12)
        undirectedGraph.addEdge(node12,node13)
        undirectedGraph.addEdge(node20,node21)
        undirectedGraph.addEdge(node21,node22)
        undirectedGraph.addEdge(node22,node23)

        val shortestDistance = getShortestDistanceFromPoints(currentNode, setOfTargetNodes, undirectedGraph)


        Assertions.assertEquals(shortestDistance.get(0).vertexList, listOf(
            Node(Point(0,0), false, null ),
            Node(Point(1,0), false, null ),
            Node(Point(2,0), false, null ),
            Node(Point(2,1), false, null )
        ))
    }
}