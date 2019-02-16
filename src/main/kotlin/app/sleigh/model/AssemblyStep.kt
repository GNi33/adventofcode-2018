package app.sleigh.model

import app.days.STEP_BASE_TIME

class AssemblyStep(val id: String) {

    val stepsBefore: MutableSet<AssemblyStep> = mutableSetOf()
    val stepsAfter: MutableSet<AssemblyStep> = mutableSetOf()

    val duration: Int by lazy {
        STEP_BASE_TIME + (id.first() - 'A' + 1)
    }

    fun isFirstStep(): Boolean {
        return stepsBefore.isEmpty()
    }

    fun isLastStep(): Boolean {
        return stepsAfter.isEmpty()
    }

    fun arePrerequisitesComplete(openSteps: Set<AssemblyStep>): Boolean {
        return stepsBefore.intersect(openSteps).isEmpty()
    }
}