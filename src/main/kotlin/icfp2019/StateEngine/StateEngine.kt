package icfp2019.StateEngine

import com.google.common.collect.ImmutableMap

class GameState {}
class RobotId {}
class Action {}

class StateEngine {
//  init {
//    this.gameState = gameState
//    this.robotActionMap = robotActionMap
//  }

  // -> Return GameState
  fun apply(currentGameState: GameState, robotIdMap: ImmutableMap<RobotId, Action>): Nothing {
    // Implement meaningful shit here
    TODO("Implement apply")
  }

//  val historyOfMapActions : MutableMap<RobotId, List<Action>>
//    get() {
//      return this.historyOfMapActions;
//    }


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