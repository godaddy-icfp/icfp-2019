package icfp2019.states

import icfp2019.Node
import icfp2019.Problem

class GameState(currentProblem: Problem) {
  val currentProblem: Problem
  init {
    this.currentProblem = currentProblem
  }

  val currentMap: Array<Array<Node>>
    get() {
      when {
        this.currentMap.isNotEmpty() -> {
          return currentProblem.map
        }
        else -> throw IllegalStateException("The currentMap is empty? ${this.currentMap}")
      }
    }

  val listOfRobotStates : MutableList<String>
    get() {
      when(this.currentProblem.)
    }


}