package app.days

import app.device.hardware.Instruction
import app.device.hardware.InstructionTerminal
import app.device.hardware.SampledInstruction
import app.util.InputParser
import mu.KotlinLogging

private const val ITERATION_CUTOFF: Long = 100

class Day16: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputParser = InputParser()
    private val instructionTerminal = InstructionTerminal()
    private val splitInput = inputParser.splitInput(DayConsts.DAY_16, "\r\n\r\n\r\n")

    override fun run() {

        logger.info { "Day 16" }
        logger.info { "Part 01" }

        val instructionInput = splitInput[0]
        val sampledInstructionInstances = inputParser.parseInput(
            SampledInstruction::class,
            instructionInput,
            "\r\n\r\n"
        )

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
        val sortedInstructions = sortAndGroupInstructions(sampledInstructionInstances)

        val codeCountMap = buildCodeCountMap(sortedInstructions)

        return resolveOpCodeIds(codeCountMap)
    }

    private fun sortAndGroupInstructions(
        sampledInstructionInstances: List<SampledInstruction>
    ): Map<Int, List<SampledInstruction>> {

        return sampledInstructionInstances
            .sortedBy { it.values[0] }
            .groupBy { it.values[0] }
    }

    private fun buildCodeCountMap(
        sortedInstructions: Map<Int, List<SampledInstruction>>
    ): MutableMap<Int, MutableMap<String, Int>> {

        val codeCountMap = mutableMapOf<Int, MutableMap<String, Int>>()

        sortedInstructions.forEach { (key, instructions) ->
            val codeCount = mutableMapOf<String, Int>()

            instructions.forEach { instruction ->
                val checkMap = instructionTerminal.tryInstruction(instruction)
                updateCodeCount(codeCount, checkMap)
            }

            codeCountMap[key] = codeCount
        }

        return codeCountMap
    }

    private fun updateCodeCount(codeCount: MutableMap<String, Int>, checkMap: Map<String, Boolean>) {
        checkMap.forEach { (code, isValid) ->
            if (isValid) {
                codeCount[code] = codeCount.getOrDefault(code, 0) + 1
            }
        }
    }

    private fun resolveOpCodeIds(codeCountMap: MutableMap<Int, MutableMap<String, Int>>): Map<Int, String> {
        val idToOpCodeMap = mutableMapOf<Int, String>()
        var iterations = 0

        while (true) {
            if (assignUniqueOpCodes(codeCountMap, idToOpCodeMap)) break
            iterations++
            if (iterations > ITERATION_CUTOFF) break
        }

        return idToOpCodeMap.toMap()
    }

    private fun assignUniqueOpCodes(
        codeCountMap: MutableMap<Int, MutableMap<String, Int>>,
        idToOpCodeMap: MutableMap<Int, String>
    ): Boolean {
        var anyAssigned = false

        codeCountMap.forEach { (key, value) ->
            if (value.count() == 1) {
                val code = value.keys.first()
                idToOpCodeMap[key] = code
                removeAssignedCodeFromOtherEntries(codeCountMap, code)
                anyAssigned = true
            }
        }

        return codeCountMap.all { it.value.isEmpty() } || !anyAssigned
    }

    private fun removeAssignedCodeFromOtherEntries(
        codeCountMap: MutableMap<Int, MutableMap<String, Int>>,
        code: String
    ) {
        codeCountMap.forEach { (_, value) ->
            value.remove(code)
        }
    }
}
