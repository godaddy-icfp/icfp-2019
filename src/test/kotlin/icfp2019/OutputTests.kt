package icfp2019

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OutputTests {
    fun getListOfAction() : MutableList<Action> {
        return mutableListOf(
            Action.MoveDown,
            Action.MoveDown,
            Action.MoveLeft,
            Action.MoveUp,
            Action.MoveUp,
            Action.MoveRight)
    }

    @Test
    fun testOutputOfOneRobot() {
        val actions = getListOfAction()
        val map = mapOf(RobotId(1) to actions)
        val actualOutput = Output.encodeRobotActions(map)

        Assertions.assertEquals("SSAWWD", actualOutput)
    }

    @Test
    fun testOutputOfTwoRobot() {
        val actions = getListOfAction()
        // Adding two robots
        val map = mapOf(
            RobotId(1) to actions,
            RobotId(2) to actions
        )
        val actualOutput = Output.encodeRobotActions(map)

        Assertions.assertEquals("SSAWWD#SSAWWD", actualOutput)
    }

    @Test
    fun testOutputOfSolutionWithTeleport() {
        val actions = getListOfAction()
        actions.add(Action.TeleportBack(Point(1, 2)))

        // Adding two robots
        val map = mapOf(
            RobotId(1) to actions,
            RobotId(2) to actions
        )

        val actualOutput = Output.encodeRobotActions(map)
        Assertions.assertEquals("SSAWWDT(1,2)#SSAWWDT(1,2)", actualOutput)
    }

    @Test
    fun testOutputOfSolutionWithManipulator() {
        val actions = getListOfAction()
        actions.add(Action.AttachManipulator(Point(1, 2)))

        // Adding two robots
        val map = mapOf(
            RobotId(1) to actions,
            RobotId(2) to actions
        )

        val actualOutput = Output.encodeRobotActions(map)
        Assertions.assertEquals("SSAWWDA(1,2)#SSAWWDA(1,2)", actualOutput)
    }
}
