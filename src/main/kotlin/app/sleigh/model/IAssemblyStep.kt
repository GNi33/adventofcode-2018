package app.sleigh.model

interface IAssemblyStep {

    val id : String
    val stepsBefore : MutableSet<IAssemblyStep>
    val stepsAfter : MutableSet<IAssemblyStep>

    fun isFirstStep(): Boolean
    fun isLastStep(): Boolean
    fun arePrerequisitesComplete(openSteps: Set<IAssemblyStep>): Boolean
}