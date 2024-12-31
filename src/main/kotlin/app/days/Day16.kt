package app.days

import app.device.hardware.Instruction
import app.device.hardware.InstructionTerminal
import app.util.InputParser
import mu.KotlinLogging

class Day16: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputParser = InputParser()
    private val instructionTerminal = InstructionTerminal()

    override fun run() {
        logger.info { "Day 16" }
        logger.info { "Part 01" }

        val splitInput = inputParser.splitInput(DayConsts.DAY_16, "\r\n\r\n\r\n")
        val instructionInput = splitInput[0]
        val instructionInstances = inputParser.parseInput(Instruction::class, instructionInput, "\r\n\r\n")

        val count = instructionTerminal.checkInstructionCodeCount(instructionInstances);
        logger.info { "Count: $count" }

        logger.info { "Part 02" }

    }
}
