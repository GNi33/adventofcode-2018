package app.sleigh.service

import app.sleigh.model.AssemblyStep

class InstructionParser(private val instructions: List<String>) {

    fun parseToAssemblySteps(): List<AssemblyStep> {

        val assemblyStepMap = instructions.flatMap {
            parseSingleInstruction(it)
        }.toMap()

        return assemblyStepMap.values.toList()
    }

    // quick and dirty
    private fun parseSingleInstruction(instruction: String): List<Pair<String, AssemblyStep>> =
        getStepNamesInSingleInstruction(instruction).map { it to AssemblyStep(it) }

    private fun getStepNamesInSingleInstruction(instruction: String): List<String> =
        instruction.split(" ").filter { it.length == 1 }
}