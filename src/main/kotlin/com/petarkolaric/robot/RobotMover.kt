package com.petarkolaric.robot

class RobotMover(var robot: Robot? = null, var table: Table = Table()) {
    fun moveRobot(input: String) {
        val command = CommandParser.parseLine(input)
        command.execute(robot, table)
    }
}