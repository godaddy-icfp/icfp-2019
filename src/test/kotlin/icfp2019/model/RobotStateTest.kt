package icfp2019.model

import icfp2019.core.ActionStateTransitionEngineKtTests
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RobotStateTest {

    @Test
    fun optimumManipulatorArmTarget() {
        var state = RobotState(RobotId(1), Point(0, 0))

        Assertions.assertEquals(Point(1,2), state.optimumManipulatorArmTarget())
        state = state.copy(RobotId(2), armRelativePoints = state.armRelativePoints.plus(Point(1,2)))
        Assertions.assertEquals(Point(1,-2), state.optimumManipulatorArmTarget())
    }
}
