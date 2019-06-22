package icfp2019.states.game

import icfp2019.Boosters
import icfp2019.Point
import icfp2019.objects.robot.RobotState

data class GameState(
  val gameBoard: Array<Short>, // TODO: Martin will replace with GameBoard Builder
  val robotStateList: List<RobotState>,
  val teleportDestination: List<Point>,
  val unusedBoosters: List<Boosters>
):w
