package app.sleigh.service

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InstructionParserTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput : List<String>
    private val instructionParser : InstructionParser

    init {
        testInput = inputReader.getDataForDay(7)
        instructionParser = InstructionParser(testInput)
    }

    @Test
    fun lazyInitAssemblySteps() {
        val assemblySteps = instructionParser.assemblySteps
        val assemblySteps2 = instructionParser.assemblySteps

        assertEquals(6, assemblySteps.size)
        assertEquals("C", assemblySteps[0].id)

        assertEquals(6, assemblySteps2.size)
        assertEquals("C", assemblySteps2[0].id)

    }

    @Test
    fun parseToAssemblySteps() {

        val assemblySteps = instructionParser.parseToAssemblySteps()

        assertEquals(6, assemblySteps.size)
        assertEquals("C", assemblySteps[0].id)
    }

}
