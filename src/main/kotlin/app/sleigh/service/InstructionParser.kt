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

    fun retrieveStepOrder(): String {

        val firstStep = getFirstStep()
        val stepsList = processStep(firstStep)

        return stepsList.joinToString("") { it.id }
    }

    private fun processStep(step: AssemblyStep, processedSteps : Set<AssemblyStep> = setOf(), openSteps : Set<AssemblyStep> = setOf()) : Set<AssemblyStep> {

        val stepsProcessed = processedSteps.union(setOf(step))

        if (step.isLastStep()) {
            return stepsProcessed
        }

        val stepsOpen = step.stepsAfter.union(openSteps).filter {
            !stepsProcessed.contains(it)
        }.sortedBy {
            it.id
        }.toSet()

        // TODO: Think of something nicer than this
        if (stepsOpen.size > 1 && stepsOpen.first().isLastStep()) {
            return processStep(stepsOpen.elementAt(1), stepsProcessed, stepsOpen)
        }

        return processStep(stepsOpen.first(), stepsProcessed, stepsOpen)

    }

    private fun getFirstStep(): AssemblyStep {
        return assemblySteps.first {
            it.isFirstStep()
        }
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