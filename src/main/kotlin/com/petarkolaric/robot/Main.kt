package com.petarkolaric.robot

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    RobotSimulator.run()
}

class RobotSimulator() {
    companion object {
        fun run() {
            val robotMover = RobotMover()
            var nextLine: String?
            println("Please enter a command:")
            while (true) {
                nextLine = readLine()
                if (nextLine == null) {
                    exitProcess(0)
                } else {
                    robotMover.moveRobot(nextLine)
                }
            }
        }
    }
}