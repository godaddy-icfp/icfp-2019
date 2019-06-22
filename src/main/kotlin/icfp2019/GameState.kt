package icfp2019

data class GameState(
    val robotStateList: List<RobotState>,
    val teleportDestination: List<Point>,
    val unusedBoosters: List<Booster>
) {
    companion object {
        val empty = GameState(listOf(), listOf(), listOf())
    }
}
