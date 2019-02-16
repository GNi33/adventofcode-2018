package app.sleigh.service

import app.sleigh.model.AssemblyStep

class TimedStepProcessor(private val assemblySteps: List<AssemblyStep>, private val numWorkers: Int) : IStepProcessor {

    var elapsedSeconds = 0

    override fun workOnSteps(): Set<AssemblyStep> {

        val firstSteps = getFirstSteps()

        return setOf()
    }

    override fun getElapsedTime(): Int {
        return elapsedSeconds
    }

    fun processStep(
        step: AssemblyStep,
        processedSteps: Set<AssemblyStep>,
        openSteps: Set<AssemblyStep>
    ): Set<AssemblyStep> {


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    private fun getFirstSteps(): List<AssemblyStep> {
        return assemblySteps.filter {
            it.isFirstStep()
        }.sortedBy { it.id }
    }
}