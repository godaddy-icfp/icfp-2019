package icfp2019.model

data class RobotState(
    val robotId: RobotId,
    val currentPosition: Point,
    val orientation: Orientation = Orientation.Right,
    val remainingFastWheelTime: Int = 0,
    val remainingDrillTime: Int = 0,
    val armRelativePoints: List<Point> = listOf(Point(1, 0), Point(1, 1), Point(1, -1))
) {
    fun optimumManipulatorArmTarget(): Point {
        // Assume all arm extensions extend the original T
        var balancePoint = 0
        armRelativePoints.forEach {
            balancePoint += it.y
        }

        var point = Point(1, - (armRelativePoints.size + 1) / 2)
        if (balancePoint <= 0) {
            point = Point(1, (armRelativePoints.size + 1) / 2)
        }
        return point
    }

    fun hasActiveDrill(): Boolean {
        return remainingDrillTime > 0
    }

    fun hasActiveFastWheels(): Boolean {
        return remainingFastWheelTime > 0
    }
}
