package com.petarkolaric.robot

import org.junit.Assert.assertEquals
import org.junit.Test

class CommandLineParserTest {

    @Test(expected = Exception::class)
    fun `should throw exception when no input string`() {
        CommandParser.parseLine("")
    }

    @Test
    fun `should return a right command for RIGHT command`() {
        val result = CommandParser.parseLine("RIGHT")

        assertEquals(result::class, RightCommand::class)
    }

    @Test
    fun `should return a left command for LEFT command`() {
        val result = CommandParser.parseLine("LEFT")

        assertEquals(result::class, LeftCommand::class)
    }

    @Test
    fun `should return a move command for MOVE command`() {
        val result = CommandParser.parseLine("MOVE")

        assertEquals(result::class, MoveCommand::class)
    }

    @Test
    fun `should return a report command for REPORT command`() {
        val result = CommandParser.parseLine("REPORT")

        assertEquals(result::class, ReportCommand::class)
    }

    @Test
    fun `should return a place command for PLACE command`() {
        val result = CommandParser.parseLine("PLACE 1,2,NORTH")

        assertEquals(result::class, PlaceCommand::class)
    }

    @Test(expected = Exception::class)
    fun `should throw an exception when place input has invalid format params`() {
        CommandParser.parseLine("PLACE 1,4,NORTHPOTATO")
    }

    @Test
    fun `should return a place command with correct x, y and direction`() {
        val result: PlaceCommand = CommandParser.parseLine("PLACE 1,2,EAST") as PlaceCommand

        assertEquals(1, result.xPosition)
        assertEquals(2, result.yPosition)
        assertEquals(Direction.EAST, result.direction)
    }
}