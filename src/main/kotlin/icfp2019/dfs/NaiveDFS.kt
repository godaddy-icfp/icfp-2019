package icfp2019.dfs

import icfp2019.Problem

class NaiveDFS(graph: Problem) {
  //TODO: Change Visited type
  val graph: Problem
  val visited: MutableList<String>
  init {
    this.graph = graph
    this.visited = mutableListOf<String>()
  }

  fun dfs() {
    this.graph.map.forEach {
      val currPoint = it.get().point
      val currX = currPoint.x
      val currY = currPoint.y

    }
  }

}
