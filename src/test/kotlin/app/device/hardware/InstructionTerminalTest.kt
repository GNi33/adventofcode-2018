package app.device.hardware

import app.device.hardware.opcode.*
import app.util.InputParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class InstructionTerminalTest {

    private val instructionTerminal = InstructionTerminal()

    @Test
    fun singleInstructionTest() {

        val instruction = Instruction()
        instruction.deserializeInput(
            "Before: [3, 2, 1, 1]\r\n" +
            "9 2 1 2\r\n" +
            "After:  [3, 2, 2, 1]"
        )

        val registers = instruction.before.toMutableList()
        Mulr().execute(instruction.values, registers)

        assertEquals(instruction.after, registers)
    }

    @Test
    fun instructionTest() {

        val registers = mutableListOf(3,2,1,1)

        Mulr().execute(listOf(9,2,1,2), registers)
        assertEquals(mutableListOf(3,2,2,1), registers)

        Muli().execute(listOf(9,1,4,2), registers)
        assertEquals(mutableListOf(3,2,8,1), registers)

        Addi().execute(listOf(9,1,4,2), registers)
        assertEquals(mutableListOf(3,2,6,1), registers)

        Addr().execute(listOf(9,1,2,3), registers)
        assertEquals(mutableListOf(3,2,6,8), registers)

        Bani().execute(listOf(9,1,5,3), registers)
        assertEquals(mutableListOf(3,2,6,0), registers)

        Banr().execute(listOf(9,1,2,2), registers)
        assertEquals(mutableListOf(3,2,2,0), registers)

        Bori().execute(listOf(9,1,5,3), registers)
        assertEquals(mutableListOf(3,2,2,7), registers)

        Borr().execute(listOf(9,1,2,2), registers)
        assertEquals(mutableListOf(3,2,2,7), registers)

        Setr().execute(listOf(9,1,2,3), registers)
        assertEquals(mutableListOf(3,2,2,2), registers)

        Seti().execute(listOf(9,7,2,3), registers)
        assertEquals(mutableListOf(3,2,2,7), registers)

        Gtir().execute(listOf(9,7,2,3), registers)
        assertEquals(mutableListOf(3,2,2,1), registers)

        Gtri().execute(listOf(9,2,2,3), registers)
        assertEquals(mutableListOf(3,2,2,0), registers)

        Gtrr().execute(listOf(9,2,2,3), registers)
        assertEquals(mutableListOf(3,2,2,0), registers)

        Eqir().execute(listOf(9,7,2,3), registers)
        assertEquals(mutableListOf(3,2,2,0), registers)

        Eqri().execute(listOf(9,2,2,3), registers)
        assertEquals(mutableListOf(3,2,2,1), registers)

        Eqrr().execute(listOf(9,2,2,3), registers)
        assertEquals(mutableListOf(3,2,2,1), registers)
    }

    @Test
    fun instructionIdentifyTest() {

        val instruction = Instruction()
        instruction.deserializeInput(
            "Before: [3, 2, 1, 1]\r\n" +
            "9 2 1 2\r\n" +
            "After:  [3, 2, 2, 1]"
        )

        val oppCodes = instructionTerminal.tryInstruction(instruction)

        val expected = mapOf(
            "addr" to false,
            "addi" to true,
            "mulr" to true,
            "muli" to false,
            "banr" to false,
            "bani" to false,
            "borr" to false,
            "bori" to false,
            "setr" to false,
            "seti" to true,
            "gtir" to false,
            "gtri" to false,
            "gtrr" to false,
            "eqir" to false,
            "eqri" to false,
            "eqrr" to false
        )

        assertEquals(expected, oppCodes)
        assertEquals(3, oppCodes.filter { it.value }.count())
    }

    @Test
    fun instructionCodeCountTest() {
        val inputParser = InputParser()

        val splitInput = inputParser.splitInput(16, "\r\n\r\n\r\n")
        val instructionInput = splitInput[0]
        val instructionInstances = inputParser.parseInput(Instruction::class, instructionInput, "\r\n\r\n")

        val count = instructionTerminal.checkInstructionCodeCount(instructionInstances);

        println(count)
    }

}
