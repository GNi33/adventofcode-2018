package app.sleigh.service

import app.sleigh.model.AssemblyStep

class InstructionParser(private val instructions: List<String>) {

    val assemblySteps: List<AssemblyStep> by lazy {
        parseToAssemblySteps()
    }

    fun parseToAssemblySteps(): List<AssemblyStep> {
        return instructions.flatMap { getStepNamesInSingleInstruction(it) }
            .distinct()
            .map {
                AssemblyStep(it)
            }
    }

    fun linkSteps(): List<AssemblyStep> {
        val stepPairs = getStepPairs()

        stepPairs.forEach {
            val sourceStep = getAssemblyStep(it.first)
            val destStep = getAssemblyStep(it.second)

            sourceStep.stepsAfter.add(destStep)
            destStep.stepsBefore.add(sourceStep)
        }

        return assemblySteps
    }

    fun getAssemblyStep(identifier: String): AssemblyStep {
        return assemblySteps.first {
            it.id == identifier
        }
    }

    fun retrieveStepOrder(stepProcessor: IStepProcessor): String {
        val stepsList = stepProcessor.workOnSteps()
        return stepsList.joinToString("") { it.id }
    }

    fun retrieveAssemblyDuration(stepProcessor: IStepProcessor): Int {
        stepProcessor.workOnSteps()
        return stepProcessor.getElapsedTime()
    }

    private fun getStepPairs(): List<Pair<String, String>> {
        return instructions.map {
            val occurringSteps = getStepNamesInSingleInstruction(it)
            Pair(occurringSteps.first(), occurringSteps.last())
        }
    }

    // quick and dirty
    private fun getStepNamesInSingleInstruction(instruction: String): List<String> =
        instruction.split(" ").filter { it.length == 1 }
}