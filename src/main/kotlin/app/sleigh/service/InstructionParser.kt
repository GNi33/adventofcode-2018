package app.sleigh.service

import app.sleigh.model.AssemblyStep

class InstructionParser(private val instructions: List<String>) {

    fun parseToAssemblySteps(): List<AssemblyStep> {

        val assemblyStepMap = instructions.flatMap { getStepNamesInSingleInstruction(it) }
            .distinct()
            .map {
                AssemblyStep(it)
            }

        return assemblyStepMap
    }

    // quick and dirty
    private fun getStepNamesInSingleInstruction(instruction: String): List<String> =
        instruction.split(" ").filter { it.length == 1 }
}