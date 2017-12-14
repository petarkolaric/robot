package com.petarkolaric.robot

import org.junit.Assert.assertEquals
import org.junit.Test

class CommandTest {

    @Test(expected = Exception::class)
    fun `left command should throw exception when no robot provided`() {
        val command = LeftCommand()
        command.execute(robot = null, table = Table())
    }

    @Test(expected = Exception::class)
    fun `right command should throw exception when no robot provided`() {
        val command = RightCommand()
        command.execute(robot = null, table = Table())
    }

    @Test(expected = Exception::class)
    fun `move command should throw exception when no robot provided`() {
        val command = MoveCommand()
        command.execute(robot = null, table = Table())
    }

    @Test(expected = Exception::class)
    fun `report command should throw exception when no robot provided`() {
        val command = ReportCommand()
        command.execute(robot = null, table = Table())
    }

    @Test
    fun `left command should rotate left`() {
        var robot = Robot(3, 3, Direction.NORTH)
        val command = LeftCommand()

        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.WEST)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.SOUTH)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.EAST)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.NORTH)
    }

    @Test
    fun `right command should rotate right`() {
        var robot = Robot(3, 3, Direction.NORTH)
        val command = RightCommand()

        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.EAST)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.SOUTH)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.WEST)
        robot = command.execute(robot, Table())
        assertEquals(robot.direction, Direction.NORTH)
    }

    @Test(expected = Exception::class)
    fun `move command should throw exception if moves robot off top of board`() {
        val robot = Robot(2,4,Direction.NORTH)
        val command = MoveCommand()
        command.execute(robot, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `move command should throw exception if moves robot off right of board`() {
        val robot = Robot(4,2,Direction.EAST)
        val command = MoveCommand()
        command.execute(robot, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `move command should throw exception if moves robot off bottom of board`() {
        val robot = Robot(4,0,Direction.SOUTH)
        val command = MoveCommand()
        command.execute(robot, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `move command should throw exception if moves robot off left of board`() {
        val robot = Robot(0,2,Direction.WEST)
        val command = MoveCommand()
        command.execute(robot, Table(5, 5))
    }

    @Test
    fun `should move robot north if facing north`() {
        var robot = Robot(2, 2, Direction.NORTH)
        val command = MoveCommand()
        robot = command.execute(robot, Table())
        assertEquals(robot.y, 3)
    }

    @Test
    fun `should move robot east if facing east`() {
        var robot = Robot(2, 2, Direction.EAST)
        val command = MoveCommand()
        robot = command.execute(robot, Table())
        assertEquals(robot.x, 3)
    }

    @Test
    fun `should move robot south if facing south`() {
        var robot = Robot(2, 2, Direction.SOUTH)
        val command = MoveCommand()
        robot = command.execute(robot, Table())
        assertEquals(robot.y, 1)
    }

    @Test
    fun `should move robot west if facing west`() {
        var robot = Robot(2, 2, Direction.WEST)
        val command = MoveCommand()
        robot = command.execute(robot, Table())
        assertEquals(robot.x, 1)
    }

    @Test
    fun `should place robot`() {
        val command = PlaceCommand(2, 4, Direction.NORTH)
        var robot: Robot? = null
        robot = command.execute(robot, Table())
        assertEquals(robot.x, 2)
        assertEquals(robot.y, 4)
        assertEquals(robot.direction, Direction.NORTH)
    }

    @Test
    fun `left command does not change passed in robot`() {
        val robot = Robot(2, 2, Direction.WEST)
        val command = LeftCommand()
        command.execute(robot, Table())
        assertEquals(robot.direction, Direction.WEST)
    }

    @Test
    fun `right command does not change passed in robot`() {
        val robot = Robot(2, 2, Direction.WEST)
        val command = RightCommand()
        command.execute(robot, Table())
        assertEquals(robot.direction, Direction.WEST)
    }

    @Test
    fun `move command does not change passed in robot`() {
        val robot = Robot(2, 2, Direction.NORTH)
        val command = MoveCommand()
        command.execute(robot, Table())
        assertEquals(robot.y, 2)
    }

    @Test
    fun `place command does not change passed in robot`() {
        val robot = Robot(1, 2, Direction.WEST)
        val command = PlaceCommand(3, 3, Direction.NORTH)
        command.execute(robot, Table())
        assertEquals(robot.x, 1)
        assertEquals(robot.y, 2)
        assertEquals(robot.direction, Direction.WEST)
    }

    @Test(expected = Exception::class)
    fun `place command should not place robot off top of table`() {
        PlaceCommand(3, 5, Direction.NORTH).execute(null, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `place command should not place robot off bottom of table`() {
        PlaceCommand(3, -1, Direction.NORTH).execute(null, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `place command should not place robot off east of table`() {
        PlaceCommand(5, 3, Direction.NORTH).execute(null, Table(5, 5))
    }

    @Test(expected = Exception::class)
    fun `place command should not place robot off west of table`() {
        PlaceCommand(-1, 3, Direction.NORTH).execute(null, Table(5, 5))
    }
}