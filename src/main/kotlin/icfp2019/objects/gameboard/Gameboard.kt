package icfp2019.objects.gameboard

import icfp2019.Node
import icfp2019.Point
import icfp2019.Problem
import java.lang.IllegalStateException
import java.util.stream.Stream

class GameBoard(_problem: Problem) {
  private val problem : Problem
  init {
    this.problem = _problem
  }

  private fun failure(message: String): Nothing {
    throw IllegalStateException(message)
  }

  // Used for Flattening Problem 2D array -> 1D of shorts
  val cells: List<Node>
    get() {
      val oneDimenMap = this.problem.map.flatten()
      when(oneDimenMap.isNotEmpty()) {
        true -> return oneDimenMap
        false -> failure("Could not initialize cells with input ${this.problem}")
      }
    }




}