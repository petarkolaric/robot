package com.petarkolaric.robot

interface Command {
    fun execute(robot: Robot?, table: Table): Robot
    fun throwExceptionIfRobotNotSet(robot: Robot?) {
        if (robot == null) {
            throw Exception("Robot has not been set")
        }
    }
}

class LeftCommand: Command {
    override fun execute(robot: Robot?, table: Table): Robot {
        throwExceptionIfRobotNotSet(robot)
        val robotCopy = robot!!.copy()
        //modulo operator doesnt wrap when ordinal underflows, so need to add 4
        robotCopy.direction = Direction.values()[(robotCopy.direction.ordinal -1 + 4) % 4]
        return robotCopy
    }
}

class RightCommand: Command {
    override fun execute(robot: Robot?, table: Table): Robot {
        throwExceptionIfRobotNotSet(robot)
        val robotCopy = robot!!.copy()
        robotCopy.direction = Direction.values()[(robotCopy.direction.ordinal +1) % 4]
        return robotCopy
    }
}

class MoveCommand: Command {
    override fun execute(robot: Robot?, table: Table): Robot {
        throwExceptionIfRobotNotSet(robot)
        val robotCopy = robot!!.copy()
        if ((robotCopy.y >= table.ySize-1 && robotCopy.direction == Direction.NORTH) ||
            (robotCopy.x >= table.xSize-1 && robotCopy.direction == Direction.EAST) ||
            (robotCopy.y <= 0 && robotCopy.direction == Direction.SOUTH) ||
            (robotCopy.x <= 0 && robotCopy.direction == Direction.WEST)) {
            throw Exception("This command will move the robot off the table")
        }
        when(robotCopy.direction) {
            Direction.NORTH -> robotCopy.y++
            Direction.EAST -> robotCopy.x++
            Direction.SOUTH -> robotCopy.y--
            Direction.WEST -> robotCopy.x--
        }
        return robotCopy
    }
}

class ReportCommand: Command {
    override fun execute(robot: Robot?, table: Table): Robot {
        throwExceptionIfRobotNotSet(robot)
        println("Robot: ${robot!!.x}, ${robot.y}, ${robot.direction}")
        return robot
    }
}

class PlaceCommand(val xPosition: Int, val yPosition: Int, val direction: Direction): Command {
    override fun execute(robot: Robot?, table: Table): Robot {
        return Robot(xPosition, yPosition, direction)
    }
}