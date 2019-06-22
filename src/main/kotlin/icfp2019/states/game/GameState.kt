package icfp2019.states.game

import icfp2019.Boosters
import icfp2019.Node
import icfp2019.Point
import icfp2019.Problem
import icfp2019.objects.gameboard.GameBoard
import icfp2019.objects.robot.RobotState

//class GameState(
//  val gameBoard: Array<Short>, // TODO: Martin will replace with GameBoard Builder
//  val robotStateList: List<RobotState>,
//  val teleportDestination: List<Point>,
//  val unusedBoosters: List<Boosters>
//):w

class GameState(_problem: Problem) {
  val problem : Problem
  init {
    this.problem = _problem
  }

  // Array<Short>
  public val gameBoard: List<Node>
    get() {
      when(this.problem.map.isNotEmpty()) {
        true -> return GameBoard(this.problem).cells
        false -> throw IllegalStateException("Problem is not initalized ${this.problem}")
      }
    }
}
