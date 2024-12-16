package app.util

import app.device.hardware.Instruction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class InputParserTest {

    private val inputParser = InputParser()

    @Test
    fun splitInputTest() {
        val splitInput = inputParser.splitInput(16, "\r\n\r\n\r\n")

        assertEquals(splitInput.count(), 2)
    }

    @Test
    fun parseInputTest() {
        val splitInput = inputParser.splitInput(16, "\r\n\r\n\r\n")

        val instructionInput = splitInput[0]

        val instructionInstances = inputParser.parseInput(Instruction::class, instructionInput, "\r\n\r\n")

        assertEquals(instructionInstances[0]::class, Instruction::class)
        assertEquals(instructionInstances.count(), 838)
    }

}
