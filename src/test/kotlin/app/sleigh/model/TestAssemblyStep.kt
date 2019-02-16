package app.sleigh.model

class TestAssemblyStep(override val id: String) : IAssemblyStep {

    override val stepsBefore: MutableSet<IAssemblyStep> = mutableSetOf()
    override val stepsAfter: MutableSet<IAssemblyStep> = mutableSetOf()

    val duration: Int by lazy {
        id.first() - 'A' + 1
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