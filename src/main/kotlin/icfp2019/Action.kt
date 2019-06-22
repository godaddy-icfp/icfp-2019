package icfp2019

sealed class Action {
    fun toSolutionString(): String = when (this) {
        MoveUp -> "W"
        MoveDown -> "S"
        MoveLeft -> "A"
        MoveRight -> "D"
        DoNothing -> "Z"
        TurnClockwise -> "E"
        TurnCounterClockwise -> "Q"
        AttachFastWheels -> "F"
        StartDrill -> "L"
        PlantTeleportResetPoint -> "R"
        CloneRobot -> "C"
        is AttachManipulator -> "A(${this.point.x}, ${this.point.y})"
        is TeleportBack -> "T(${this.targetResetPoint.x},${this.targetResetPoint.x})"
    }

    fun invert(): Action? = when (this) {
        MoveRight -> MoveLeft
        MoveLeft -> MoveRight
        MoveDown -> MoveUp
        MoveUp -> MoveDown
        else -> null
    }
}

object MoveUp : Action()
object MoveDown : Action()
object MoveLeft : Action()
object MoveRight : Action()
object DoNothing : Action()
object TurnClockwise : Action()
object TurnCounterClockwise : Action()
data class AttachManipulator(val point: Point) : Action()
object AttachFastWheels : Action()
object StartDrill : Action()
object PlantTeleportResetPoint : Action()
data class TeleportBack(val targetResetPoint: Point) : Action()
object CloneRobot : Action()
