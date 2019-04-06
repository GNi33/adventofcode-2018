package app.sleigh.service

import app.sleigh.model.IAssemblyStep

class StepProcessor(private val assemblySteps: List<IAssemblyStep>) : IStepProcessor {

    var elapsedSeconds = 0

    override fun workOnSteps(): Set<IAssemblyStep> {
        // TODO this should be nicer
        val firstSteps = getFirstSteps()
        val firstStep = firstSteps.first()
        val otherSteps = firstSteps.filter { it.id != firstStep.id }.toSet()

        return processStep(firstStep, openSteps = otherSteps)
    }

    override fun getElapsedTime(): Int {
        return elapsedSeconds
    }

    private fun processStep(
        step: IAssemblyStep,
        processedSteps: Set<IAssemblyStep> = setOf(),
        openSteps: Set<IAssemblyStep> = setOf()
    ): Set<IAssemblyStep> {

        val stepsProcessed = processedSteps.union(setOf(step))
        elapsedSeconds += 1

        if (step.isLastStep()) {
            return stepsProcessed
        }

        val stepsOpen = step.stepsAfter.union(openSteps).filter {
            !stepsProcessed.contains(it)
        }.sortedBy {
            it.id
        }.toSet()

        // TODO it should be possible to make this nicer
        for (openStep in stepsOpen) {
            if (openStep.arePrerequisitesComplete(stepsOpen)) {
                return processStep(openStep, stepsProcessed, stepsOpen)
            }
        }

        // TODO better exception
        throw Exception("No valid Step found to proceed further. Something went seriously wrong")
    }

    private fun getFirstSteps(): List<IAssemblyStep> {
        return assemblySteps.filter {
            it.isFirstStep()
        }.sortedBy { it.id }
    }
}