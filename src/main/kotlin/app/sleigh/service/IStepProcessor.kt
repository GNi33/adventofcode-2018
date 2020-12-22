package app.sleigh.service

import app.sleigh.model.IAssemblyStep

interface IStepProcessor {

    fun workOnSteps(): Set<IAssemblyStep>
    fun getElapsedTime(): Int
}
