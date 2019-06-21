package icfp2019

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


@Test
fun testParser() {
  val result = parseDesc(ProblemDescription(ProblemId(234), "(1,2),(3,4)"))
  Assertions.assertEquals(result, listOf(Point(1, 2), Point(3, 4)))
}

@Test
fun testFullFile() {
  val testInput = "(30,24),(32,24),(32,21),(33,21),(33,19),(34,19),(34,18),(31,18),(31,22),(28,22),(28,18),(29,18),(29,17),(27,17),(27,23)," +
      "(26,23),(26,43),(14,43),(14,23),(16,23),(16,21),(6,21),(6,31),(0,31),(0,21),(2,21),(2,0),(16,0),(16,11),(20,11),(20,23)," +
      "(21,23),(21,17),(24,17),(24,14),(29,14),(29,15),(34,15),(34,16),(36,16),(36,21),(35,21),(35,24),(34,24),(34,25),(36,25)," +
      "(36,22),(39,22),(39,23),(42,23),(42,26),(39,26),(39,25),(38,25),(38,31),(36,31),(36,34),(33,34),(33,31),(34,31),(34,28)," +
      "(30,28)#(0,21)#(6,10),(8,10),(8,1),(11,1),(11,10),(12,10),(12,14),(15,14),(15,15),(12,15),(12,16),(10,16),(10,17),(9,17)," +
      "(9,16),(6,16),(6,14),(5,14),(5,15),(4,15),(4,14),(3,14),(3,13),(6,13),(6,12),(3,12),(3,11),(6,11);(18,26),(19,26),(19,24)," +
      "(21,24),(21,26),(24,26),(24,29),(25,29),(25,30),(24,30),(24,32),(22,32),(22,33),(25,33),(25,34),(24,34),(24,40),(23,40)," +
      "(23,34),(22,34),(22,35),(21,35),(21,38),(20,38),(20,37),(16,37),(16,36),(20,36),(20,35),(19,35),(19,32),(18,32),(18,30)," +
      "(17,30),(17,29),(18,29),(18,28),(15,28),(15,27),(16,27),(16,24),(17,24),(17,27),(18,27)" +
      "#X(9,19);L(17,13);F(5,6);F(13,19);F(14,4);B(16,18);B(5,8)"

  val problem = ProblemDescription(ProblemId(34), testInput)
  val p = parseDesc(problem)
}

@Test
fun testObstacleMap() {

  val nodeMap = arrayOf(
          arrayOf(Node(Point(0,0),false,null),
          Node(Point(0,1),false,null),
          Node(Point(0,2),true,null),
          Node(Point(0,3),false,null)),

          arrayOf(Node(Point(1,0),true,null),
                  Node(Point(1,1),false,null),
                  Node(Point(1,2),true,null),
                  Node(Point(1,3),false,null))

  )

  val problem = Problem(ProblemId(1),Point(0,0), nodeMap)
  val obstacle = constructObstacleMap(problem)

  val expected = arrayOf(
          arrayOf(false, false, true, false),
          arrayOf(true, false, true, false)
  )

  Assertions.assertEquals(obstacle, expected)

}

