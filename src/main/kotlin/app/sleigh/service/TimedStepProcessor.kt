package app.sleigh.service

import app.sleigh.model.AssemblyStep
import app.sleigh.model.IAssemblyStep

class TimedStepProcessor(private val assemblySteps: List<IAssemblyStep>, private val numWorkers: Int) : IStepProcessor {

    var elapsedSeconds = 0
    var workersOccupied = 0

    override fun workOnSteps(): Set<IAssemblyStep> {

        val firstSteps = getFirstSteps()

        return setOf()
    }

    override fun getElapsedTime(): Int {
        return elapsedSeconds
    }

    fun processSecond() {

    }

    fun processStep(
        step: AssemblyStep,
        processedSteps: Set<AssemblyStep>,
        openSteps: Set<AssemblyStep>
    ): Set<AssemblyStep> {


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    private fun getFirstSteps(): List<IAssemblyStep> {
        return assemblySteps.filter {
            it.isFirstStep()
        }.sortedBy { it.id }
    }
}