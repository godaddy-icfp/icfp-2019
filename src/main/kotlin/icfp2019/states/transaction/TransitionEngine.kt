package icfp2019.states.transaction

import com.google.common.collect.ImmutableMap
import icfp2019.GameState
import icfp2019.objects.robot.RobotId
import icfp2019.states.actions.Actions

class TransitionEngine {
  // -> Return GameState
  fun apply(currentGameState: GameState, robotIdMap: ImmutableMap<RobotId, Actions>): Nothing {
    // Implement meaningful shit here
    TODO("Implement apply")
  }
}