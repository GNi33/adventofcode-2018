package app.sleigh.service

import app.sleigh.model.AssemblyStep

class InstructionParser(private val instructions : List<String>) {

    fun parseToAssemblySteps() : List<AssemblyStep> {

        val assemblyStepMap = instructions.map {
            "A" to AssemblyStep("A")
        }.toMap()

        return assemblyStepMap.values.toList()
    }

    private fun parseSingleInstruction(instruction: String) : List<Pair<String, AssemblyStep>> {
        return listOf("A" to AssemblyStep("A"), "B" to AssemblyStep("B"))
    }

}