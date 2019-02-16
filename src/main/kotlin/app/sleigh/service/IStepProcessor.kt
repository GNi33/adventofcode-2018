package app.sleigh.service

import app.sleigh.model.AssemblyStep

interface IStepProcessor {

    fun workOnSteps() : Set<AssemblyStep>
    fun getElapsedTime() : Int
}