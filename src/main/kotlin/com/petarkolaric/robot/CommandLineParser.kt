package com.petarkolaric.robot

class CommandParser {
    companion object {
        fun parseLine(lineToParse: String): Command {
            val placeRegex = Regex("PLACE [0-9]+,[0-9]+,(NORTH|EAST|SOUTH|WEST)")
            if(placeRegex.matches(lineToParse)) {
                val placeParams = lineToParse.split(' ')[1].split(',')
                return PlaceCommand(
                        xPosition = placeParams[0].toInt(),
                        yPosition = placeParams[1].toInt(),
                        direction = Direction.valueOf(placeParams[2])
                )
            }
            when(lineToParse) {
                "LEFT" -> return LeftCommand()
                "RIGHT" -> return RightCommand()
                "MOVE" -> return MoveCommand()
                "REPORT" -> return ReportCommand()
                else -> throw Exception("Invalid input")
            }
        }
    }
}