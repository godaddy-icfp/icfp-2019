package icfp2019.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RobotStateTest {

    @Test
    fun optimumManipulatorArmTarget() {
        var state = RobotState(RobotId.first, Point(0, 0))

        Assertions.assertEquals(Point(1, 2), state.optimumManipulatorArmTarget())
        state = state.copy(RobotId.first, armRelativePoints = state.armRelativePoints.plus(Point(1, 2)))
        Assertions.assertEquals(Point(1, -2), state.optimumManipulatorArmTarget())
    }
}
