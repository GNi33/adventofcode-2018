package app.sleigh.model

interface IAssemblyStep {

    val id: String
    val stepsBefore: MutableSet<IAssemblyStep>
    val stepsAfter: MutableSet<IAssemblyStep>
    val duration: Int

    var progress: Int
    var isInProgress: Boolean
    var isDone: Boolean

    fun isFirstStep(): Boolean
    fun isLastStep(): Boolean
    fun arePrerequisitesComplete(openSteps: Set<IAssemblyStep>): Boolean
}
