package icfp2019.model

<<<<<<< HEAD
=======
import icfp2019.core.ActionStateTransitionEngineKtTests
>>>>>>> 85f1fceaf56c010818e100ea9ff8d9d5cb328844
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
