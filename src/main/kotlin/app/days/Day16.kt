package app.days

import app.device.hardware.Instruction
import app.device.hardware.InstructionTerminal
import app.device.hardware.SampledInstruction
import app.util.InputParser
import mu.KotlinLogging

class Day16: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputParser = InputParser()
    private val instructionTerminal = InstructionTerminal()
    private val splitInput = inputParser.splitInput(DayConsts.DAY_16, "\r\n\r\n\r\n")

    override fun run() {

        logger.info { "Day 16" }
        logger.info { "Part 01" }

        val instructionInput = splitInput[0]
        val sampledInstructionInstances = inputParser.parseInput(SampledInstruction::class, instructionInput, "\r\n\r\n")

        val count = instructionTerminal.checkInstructionCodeCount(sampledInstructionInstances);
        logger.info { "Count: $count" }

        logger.info { "Part 02" }

        instructionTerminal.resetRegisters()
        val opCodeIds = determineOpCodeIds(sampledInstructionInstances)
        instructionTerminal.resetRegisters()

        val programInput = splitInput[1]
        val programInstructions = inputParser.parseInput(Instruction::class, programInput, "\r\n")

        instructionTerminal.runInstructions(programInstructions, opCodeIds)

        logger.info { "Registers: ${instructionTerminal.registers}" }
    }

    fun determineOpCodeIds(sampledInstructionInstances: List<SampledInstruction>): Map<Int, String> {
        val sortedInstructions = sampledInstructionInstances.sortedBy { it.values[0] }.groupBy { it.values[0] }

        val codeCountMap = mutableMapOf<Int, MutableMap<String, Int>>()

        sortedInstructions.forEach { (key, instructions) ->
            val codeCount = mutableMapOf<String, Int>()

            instructions.forEach { instruction ->
                val checkMap = instructionTerminal.tryInstruction(instruction)

                checkMap.forEach { (code, isValid) ->
                    if (isValid) {
                        codeCount[code] = codeCount.getOrDefault(code, 0) + 1
                    }
                }
            }
            codeCountMap[key] = codeCount
        }

        val idToOpCodeMap = mutableMapOf<Int, String>()

        var ct = 0
        while (true) {

            codeCountMap.forEach { (key, value) ->
                if (value.count() == 1) {
                    val code = value.keys.first()
                    idToOpCodeMap[key] = code

                    codeCountMap.forEach { (k, v) ->
                        v.remove(code)
                    }
                }
            }

            ct += 1

            if (codeCountMap.all { it.value.count() == 0 } || ct > 100) {
                break
            }
        }

        return idToOpCodeMap.toMap()
    }
}
