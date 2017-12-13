package com.petarkolaric.robot

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Please enter a command:")
    val robotMover = RobotMover()
    var nextLine: String?
    while (true) {
        nextLine = readLine()
        if (nextLine == null) {
            exitProcess(0)
        } else {
            robotMover.moveRobot(nextLine)
        }
    }
}