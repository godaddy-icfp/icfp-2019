package icfp2019.GameEngine

import com.google.common.collect.ImmutableMap
import icfp2019.Node
import icfp2019.Problem

class GameState {}
class RobotId {}
class Action {}

class GameEngine(gameState: GameState, robotActionMap: ImmutableMap<RobotId, Action>) : GameState {
  val gameState: GameState
  val robotActionMap: ImmutableMap<RobotId, Action>
  init {
    this.gameState = gameState
    this.robotActionMap = robotActionMap
  }

  // -> Return GameSatte
  fun apply(currentGameState: GameState, robotIdMap: ImmutableMap<RobotId, Action>): Nothing {
    // Implement meaningful shit here
    return Nothing
  }

//  val historyMap: ImmutableMap<RobotId, Action>
//    get() {
//      val immutableMap = ImmutableMap.of<RobotId, Action>()
//      when {
//        this.RobotId.isNotEmpty() -> {
//          immutableMap.
//        }
//        else -> throw IllegalStateException(“The currentMap is empty? ${this.currentMap}“)
//      }
//    }
//
//  val listOfRobotStates : MutableList<String>
//    get() {
//      when(this.currentProblem.)
//    }


}