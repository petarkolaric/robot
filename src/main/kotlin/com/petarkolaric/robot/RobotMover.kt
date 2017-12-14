package com.petarkolaric.robot

class RobotMover() {
    var robot: Robot? = null
    var table: Table = Table()
    fun moveRobot(input: String) {
        try {
            val command = CommandParser.parseLine(input)
            robot = command.execute(robot, table)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}