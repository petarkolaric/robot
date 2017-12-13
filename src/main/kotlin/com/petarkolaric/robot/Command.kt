package com.petarkolaric.robot

interface Command {
    fun execute(robot: Robot?, table: Table)
    fun throwExceptionIfRobotNotSet(robot: Robot?) {
        if (robot == null) {
            throw Exception("Robot has not been set")
        }
    }
}

class LeftCommand: Command {
    override fun execute(robot: Robot?, table: Table) {
        throwExceptionIfRobotNotSet(robot)
        //modulo operator doesnt wrap when ordinal underflows, so need to add 4
        robot!!.direction = Direction.values()[(robot.direction.ordinal -1 + 4) % 4]
    }
}

class RightCommand: Command {
    override fun execute(robot: Robot?, table: Table) {
        throwExceptionIfRobotNotSet(robot)
        robot!!.direction = Direction.values()[(robot.direction.ordinal +1) % 4]
    }
}

class MoveCommand: Command {
    override fun execute(robot: Robot?, table: Table) {
        throwExceptionIfRobotNotSet(robot)
        if ((robot!!.y >= table.ySize-1 && robot.direction == Direction.NORTH) ||
            (robot.x >= table.xSize-1 && robot.direction == Direction.EAST) ||
            (robot.y <= 0 && robot.direction == Direction.SOUTH) ||
            (robot.x <= 0 && robot.direction == Direction.WEST)) {
            throw Exception("This command will move the robot off the table")
        }
        when(robot.direction) {
            Direction.NORTH -> robot.y++
            Direction.EAST -> robot.x++
            Direction.SOUTH -> robot.y--
            Direction.WEST -> robot.x--
        }
    }
}

class ReportCommand: Command {
    override fun execute(robot: Robot?, table: Table) {
        throwExceptionIfRobotNotSet(robot)
        println("Robot: ${robot!!.x}, ${robot.y}, ${robot.direction}")
    }
}

class PlaceCommand(val xPosition: Int, val yPosition: Int, val direction: Direction): Command {
    override fun execute(robot: Robot?, table: Table) {
//        robot.x = xPosition
//        robot.y = yPosition
//        robot.direction = direction
    }
}