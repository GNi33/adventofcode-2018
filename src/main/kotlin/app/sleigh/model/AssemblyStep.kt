package app.sleigh.model

import app.days.DayConsts.ConstsDay7.STEP_BASE_TIME

class AssemblyStep(override val id: String) : IAssemblyStep {

    override val stepsBefore: MutableSet<IAssemblyStep> = mutableSetOf()
    override val stepsAfter: MutableSet<IAssemblyStep> = mutableSetOf()

    override var progress: Int = 0
    override var isInProgress: Boolean = false
    override var isDone: Boolean = false

    override val duration: Int by lazy {
        STEP_BASE_TIME + (id.first() - 'A' + 1)
    }

    override fun isFirstStep(): Boolean {
        return stepsBefore.isEmpty()
    }

    override fun isLastStep(): Boolean {
        return stepsAfter.isEmpty()
    }

    override fun arePrerequisitesComplete(openSteps: Set<IAssemblyStep>): Boolean {
        return stepsBefore.intersect(openSteps).isEmpty()
    }
}
