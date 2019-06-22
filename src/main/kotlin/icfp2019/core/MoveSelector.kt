package icfp2019.core

import icfp2019.model.GameBoard
import icfp2019.model.GameState

interface MoveSelector {
    fun selectFrom(strategies: Iterable<Strategy>): (map: GameBoard) -> (robotId: RobotId, state: GameState) -> Proposal
}
