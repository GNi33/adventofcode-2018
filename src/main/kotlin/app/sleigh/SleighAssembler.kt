package app.sleigh

import app.days.DayConsts
import app.sleigh.service.InstructionParser
import app.sleigh.service.StepProcessor
import app.sleigh.service.TimedStepProcessor
import app.util.IInputReader
import org.koin.core.KoinComponent
import org.koin.core.inject

class SleighAssembler : KoinComponent {

    private val inputReader by inject<IInputReader>()

    fun getOrderOfSteps(): String {
        val sleighAssemblyData = inputReader.getDataForDay(DayConsts.DAY_7)
        val instructionParser = InstructionParser(sleighAssemblyData)

        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = StepProcessor(linkedSteps)

        return instructionParser.retrieveStepOrder(stepProcessor)
    }

    fun getTimeSpent(): Int {
        val sleighAssemblyData = inputReader.getDataForDay(DayConsts.DAY_7)
        val instructionParser = InstructionParser(sleighAssemblyData)

        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = TimedStepProcessor(linkedSteps, 5)

        return instructionParser.retrieveAssemblyDuration(stepProcessor)
    }
}