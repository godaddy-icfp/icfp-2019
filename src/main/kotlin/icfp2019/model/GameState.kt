package icfp2019.model

data class GameState(
    val robotState: RobotState, // For now just one robot -- eventually there will be more
    val robotStateList: List<RobotState>,
    val teleportDestination: List<Point>,
    val unusedBoosters: List<Booster>
) {
    companion object {
        fun empty(startingPoint: Point) =
            GameState(
                RobotState(RobotId(0), startingPoint),
                listOf(RobotState(RobotId(0), startingPoint)),
                listOf(),
                listOf()
            )
    }

    private fun updateRobotWithPoint(requested : Int, point : Point): List<RobotState> {
        return this.robotStateList.mapIndexed { index, robotState ->
            if (index == requested) {
                robotState.copy(currentPosition = point)
            } else {
                robotState
            }
        }
    }

    // Given a robot and a point, return new state
    fun applyRobotPoint(robotIndex : Int, point : Point): GameState {
        return this.copy(robotStateList = updateRobotWithPoint(robotIndex, point))
    }
}
