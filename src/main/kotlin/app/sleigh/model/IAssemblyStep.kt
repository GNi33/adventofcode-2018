package app.sleigh.model

import app.model.ProgressStepInterface

interface IAssemblyStep: ProgressStepInterface {

    val id: String
    val stepsBefore: MutableSet<IAssemblyStep>
    val stepsAfter: MutableSet<IAssemblyStep>
    val duration: Int

    fun isFirstStep(): Boolean
    fun isLastStep(): Boolean
    fun arePrerequisitesComplete(openSteps: Set<IAssemblyStep>): Boolean
}
