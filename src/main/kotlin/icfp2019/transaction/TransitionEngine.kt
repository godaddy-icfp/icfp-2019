package icfp2019.transaction

import com.google.common.collect.ImmutableMap
import icfp2019.Actions
import icfp2019.RobotId


class GameState {}

class TransitionEngine {
  // -> Return GameState
  fun apply(currentGameState: GameState, robotIdMap: ImmutableMap<RobotId, Actions>): Nothing {
    // Implement meaningful shit here
    TODO("Implement apply")
  }
}