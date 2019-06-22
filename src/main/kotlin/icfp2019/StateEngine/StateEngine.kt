package icfp2019.StateEngine

import com.google.common.collect.ImmutableMap

class GameState {}
class RobotId {}
class Action {}

class GameEngine(_actionMap: ImmutableMap<RobotId, Action>) {
  val actionMap: ImmutableMap<RobotId, Action> = _actionMap
    get() = field

  // -> Return GameState
  fun apply(currentGameState: GameState, robotIdMap: ImmutableMap<RobotId, Action>): Nothing {
    // Implement meaningful shit here
    TODO("Implement apply")
  }

  // Pass in some straegy
  fun strategy() : Strategies {
    TODO("Implement something logical")
  }

}