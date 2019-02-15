package app.sleigh

import app.sleigh.service.InstructionParser
import app.util.IInputReader
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SleighAssembler : KoinComponent {

    private val inputReader by inject<IInputReader>()

    fun getOrderOfSteps(): String {
        val sleighAssemblyData = inputReader.getDataForDay(7)
        val instructionParser = InstructionParser(sleighAssemblyData)

        instructionParser.linkSteps()

        return instructionParser.retrieveStepOrder()
    }
}