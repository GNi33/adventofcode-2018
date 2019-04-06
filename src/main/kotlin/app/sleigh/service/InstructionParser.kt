package app.sleigh.service

import app.sleigh.model.IAssemblyStep
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class InstructionParser(private val instructions: List<String>) : KoinComponent {

    val assemblySteps: List<IAssemblyStep> by lazy {
        parseToAssemblySteps()
    }

    fun parseToAssemblySteps(): List<IAssemblyStep> {
        return instructions.flatMap { getStepNamesInSingleInstruction(it) }
            .distinct()
            .map {
                val assemblyStep: IAssemblyStep by inject { parametersOf(it) }
                assemblyStep
            }
    }

    fun linkSteps(): List<IAssemblyStep> {
        val stepPairs = getStepPairs()

        stepPairs.forEach {
            val sourceStep = getAssemblyStep(it.first)
            val destStep = getAssemblyStep(it.second)

            sourceStep.stepsAfter.add(destStep)
            destStep.stepsBefore.add(sourceStep)
        }

        return assemblySteps
    }

    fun getAssemblyStep(identifier: String): IAssemblyStep {
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