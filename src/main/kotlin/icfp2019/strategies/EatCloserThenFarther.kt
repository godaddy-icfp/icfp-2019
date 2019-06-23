package icfp2019.strategies

import icfp2019.analyzers.DistanceToWalls
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.core.Strategy
import icfp2019.model.Action

class EatCloserThenFarther : Strategy {
    override fun compute(map: GameBoard): (state: GameState) -> Iterable<Action> {
        val distanceToWallsAnalyzer = DistanceToWalls().analyze(map)
        return { state ->
            // Assume one robot only

            // val currentDistance = distanceToWallsAnalyzer(0, state)
            val points = listOf(
                    0 to (Action.MoveUp to state.robotStateList[0].currentPosition.up()),
                    1 to (Action.MoveRight to state.robotStateList[0].currentPosition.right()),
                    2 to (Action.MoveDown to state.robotStateList[0].currentPosition.down()),
                    3 to (Action.MoveLeft to state.robotStateList[0].currentPosition.left()))

            // [Index, GameState]
            val newStates = points
                .filter {
                    it.second.second.x >= 0 && it.second.second.x < map.width &&
                            it.second.second.y >= 0 && it.second.second.y < map.height }
                .map { it.first to state.applyRobotPoint(0, it.second.second) }
            // [Index, distance]
            val newValues = newStates
                .map { it.first to distanceToWallsAnalyzer(0, it.second) }
                .filter { it.second >= 0 }

            // Deal with wrapped vs unwrapped. If all wrapped, go for the largest.
            // Else, go for the smallest.
            val allWrapped = newValues
                .map { it.first }
                .filter { !map.get(points[it].second.second).isWrapped }
                .isEmpty()

            val result = if (allWrapped) {
                newValues
                    .maxBy { it.second }
            } else {
                newValues
                    .minBy { it.second }
            }

            if (result != null) {
                //Proposal(DistanceEstimate(0), points[result.first].second.first)
                listOf(points[result.first].second.first)
            } else {
                //Proposal(DistanceEstimate(0), Action.DoNothing)
                listOf(Action.DoNothing)
            }
        }
    }
}
