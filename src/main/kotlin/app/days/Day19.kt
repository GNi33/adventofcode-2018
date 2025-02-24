package app.days

import app.days.DayConsts.DAY_19
import app.device.hardware.InstructionTerminal
import app.device.hardware.NamedInstruction
import app.util.InputParser
import app.util.InputReader
import mu.KotlinLogging

class Day19: IDay {
    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val inputParser = InputParser()

    override fun run() {
        logger.info { "Day 19 - Go With The Flow" }
        logger.info { "Part 01" }

        val instructionTerminal = InstructionTerminal()

        val programInput = inputReader.getDataForDay(DAY_19).toMutableList()

        val instructionPointer = programInput.removeFirst().substringAfter(" ").toInt()
        instructionTerminal.instructionPointer = 0
        instructionTerminal.pointerRegistry = instructionPointer

        val programInstructions = inputParser.parseInputBlocks(NamedInstruction::class, programInput)

        instructionTerminal.runNamedInstructions(programInstructions)

        logger.info { "Register 0: ${instructionTerminal.registers[0]}" }

        logger.info { "Part 02" }

        //instructionTerminal.resetRegisters()
        //instructionTerminal.registers[0] = 1

        //instructionTerminal.runNamedInstructions(programInstructions)

    }
}
