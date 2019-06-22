package icfp2019.states.game

import icfp2019.Boosters
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

  public val gameBoard: Array<Short>
    get() {
      if(this.problem.map.isNotEmpty()) {
        return GameBoard(this.problem)
      }
    }
}
