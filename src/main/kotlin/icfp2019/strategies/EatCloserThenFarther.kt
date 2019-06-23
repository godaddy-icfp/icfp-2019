package icfp2019.strategies

import icfp2019.analyzers.DistanceToWalls
import icfp2019.core.DistanceEstimate
import icfp2019.core.Proposal
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.core.Strategy2
import icfp2019.core.applyAction
import icfp2019.model.Action
import icfp2019.model.RobotId

class EatCloserThenFarther : Strategy2 {
    override fun compute(initialState: GameState): (state: GameState) -> Proposal {
        val distanceToWallsAnalyzer = DistanceToWalls().analyze(initialState)
        return { state ->
            // Assume one robot only

            // val currentDistance = distanceToWallsAnalyzer(0, state)
            val initialRobot = RobotId(0)
            val points = listOf(
                    0 to (Action.MoveUp to state.robotState[initialRobot]!!.currentPosition.up()),
                    1 to (Action.MoveRight to state.robotState[initialRobot]!!.currentPosition.right()),
                    2 to (Action.MoveDown to state.robotState[initialRobot]!!.currentPosition.down()),
                    3 to (Action.MoveLeft to state.robotState[initialRobot]!!.currentPosition.left()))

            // [Index, GameState]
            val newStates = points
                .filter {
                    it.second.second.x >= 0 && it.second.second.x < state.mapSize.x &&
                            it.second.second.y >= 0 && it.second.second.y < state.mapSize.y }
                .map { it.first to applyAction(state, initialRobot, it.second.first) }
            // [Index, distance]
            val newValues = newStates
                .map { it.first to distanceToWallsAnalyzer(initialRobot, it.second) }
                .filter { it.second >= 0 }

            // Deal with wrapped vs unwrapped. If all wrapped, go for the largest.
            // Else, go for the smallest.
            val allWrapped = newValues
                .map { it.first }
                .filter { !state.get(points[it].second.second).isWrapped }
                .isEmpty()

            val result = if (allWrapped) {
                newValues
                    .maxBy { it.second }
            } else {
                newValues
                    .minBy { it.second }
            }

            if (result != null) {
                Proposal(DistanceEstimate(0), points[result.first].second.first)
            } else {
                Proposal(DistanceEstimate(0), Action.DoNothing)
            }
        }
    }
}
