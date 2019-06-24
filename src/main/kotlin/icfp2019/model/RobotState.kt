package icfp2019.model

import kotlin.math.PI
import kotlin.math.roundToInt

data class RobotState(
    val robotId: RobotId,
    val currentPosition: Point,
    val orientation: Orientation = Orientation.Right,
    val remainingFastWheelTime: Int = 0,
    val remainingDrillTime: Int = 0,
    val armRelativePoints: List<Point> = listOf(Point(1, 0), Point(1, 1), Point(1, -1))
) {
    fun optimumManipulatorArmTarget(): Point {
        val areaToExtend = armRelativePoints.count() % 3
        return when (areaToExtend) {
            0 -> {
                // left
                val cuddlePoint = Point(-1, 0)
                if (!armRelativePoints.contains(cuddlePoint)) {
                    // have to return this explicitly because minBy(x) would be the robot for first extra arm
                    cuddlePoint
                } else {
                    val pointToExtend = armRelativePoints.minBy { it.x }!!
                    Point(pointToExtend.x - 1, pointToExtend.y)
                }
            }
            1 -> {
                // down
                val pointToExtend = armRelativePoints.minBy { it.y }!!
                Point(pointToExtend.x, pointToExtend.y - 1)
            }
            else -> {
                // up
                val pointToExtend = armRelativePoints.maxBy { it.y }!!
                Point(pointToExtend.x, pointToExtend.y + 1)
            }
        }
    }

    fun hasActiveDrill(): Boolean {
        return remainingDrillTime > 1
    }

    fun hasActiveFastWheels(): Boolean {
        return remainingFastWheelTime > 1
    }

    fun turnClockwise(): List<Point> {
        return armRelativePoints
            .map { rotatePoint(it, PI / 2) }
    }

    fun turnCounterClockwise(): List<Point> {
        return armRelativePoints
            .map { rotatePoint(it, -PI / 2) }
    }

    fun rotatePoint(point: Point, theta: Double): Point {
        val x = point.x
        val y = point.y
        return Point(
            ((Math.cos(theta) * x - Math.sin(theta) * y).roundToInt()),
            ((Math.cos(theta) * y + Math.sin(theta) * x).roundToInt())
        )
    }
}
