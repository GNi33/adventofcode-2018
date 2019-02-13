package app.sleigh.service

import app.sleigh.model.AssemblyStep

class InstructionParser(private val instructions: List<String>) {

    val assemblySteps : List<AssemblyStep> by lazy {
        parseToAssemblySteps()
    }

    fun parseToAssemblySteps(): List<AssemblyStep> {
        return instructions.flatMap { getStepNamesInSingleInstruction(it) }
            .distinct()
            .map {
                AssemblyStep(it)
            }
    }

    // quick and dirty
    private fun getStepNamesInSingleInstruction(instruction: String): List<String> =
        instruction.split(" ").filter { it.length == 1 }
}