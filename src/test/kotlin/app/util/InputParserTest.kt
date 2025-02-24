package app.util

import app.device.hardware.Instruction
import app.device.hardware.SampledInstruction
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
    fun parseSampledInstructionInputTest() {
        val splitInput = inputParser.splitInput(16, "\r\n\r\n\r\n")

        val instructionInput = splitInput[0]

        val sampledInstructionInstances = inputParser.parseInput(
            SampledInstruction::class,
            instructionInput,
            "\r\n\r\n"
        )

        assertEquals(sampledInstructionInstances[0]::class, SampledInstruction::class)
        assertEquals(sampledInstructionInstances.count(), 838)
    }

    @Test
    fun parseInstructionInputTest() {
        val splitInput = inputParser.splitInput(16, "\r\n\r\n\r\n")

        val instructionInput = splitInput[1]
        val sampledInstructionInstances = inputParser.parseInput(Instruction::class, instructionInput, "\r\n")

        assertEquals(sampledInstructionInstances[0]::class, Instruction::class)
        assertEquals(sampledInstructionInstances.count(), 836)
    }

}
