package icfp2019.objects.gameboard

import icfp2019.Node
import icfp2019.Point
import icfp2019.Problem
import java.util.stream.Stream

class GameBoard(_problem: Problem) {
  val problem : Problem
  init {
    this.problem = _problem
  }

  // Used for Flattening Problem 2D array -> 1D of shorts
  val cells: List<Node> = listOf<Node>()
    get() = this.problem.map.flatten()
    get() {
      val oneDimenMap = this.problem.map.flatten()
      when(oneDimenMap.isNotEmpty()) {
        true -> return oneDimenMap
        false -> throw IllegalStateExcepton("Could not initialize cells with input ${this.problem}")
      }
    }
    set(value) {
      when(value.isNotEmpty()) {
        true -> field = value
        false -> throw IllegalStateException("Incorrect input value ${value}")
      }
    }

}