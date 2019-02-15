package app.sleigh.model

class AssemblyStep(val id: String) {

    val stepsBefore : MutableSet<AssemblyStep> = mutableSetOf()
    val stepsAfter: MutableSet<AssemblyStep> = mutableSetOf()

    fun isFirstStep(): Boolean {
        return stepsBefore.isEmpty()
    }

    fun isLastStep(): Boolean {
        return stepsAfter.isEmpty()
    }

    fun arePrerequisitsComplete(openSteps: Set<AssemblyStep>) : Boolean {
        return stepsBefore.intersect(openSteps).isEmpty()
    }
}