package app.sleigh.service

import app.util.IInputReader
import app.util.InputReader
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.NoSuchElementException

internal class InstructionParserTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String>
    private val instructionParser: InstructionParser

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

    @Test
    fun linkAssemblySteps() {
        val assemblySteps = instructionParser.linkSteps()
        assertEquals(listOf("A", "F"), assemblySteps[0].stepsAfter.map { it.id })
    }

    @Test(expected = NoSuchElementException::class)
    fun getNonExistingStep() {
        instructionParser.getAssemblyStep("Z")
    }

    @Test
    fun retrieveCorrectStepOrder() {

        instructionParser.linkSteps()

        val stepOrder = instructionParser.retrieveStepOrder()

        assertEquals("CABDFE", stepOrder)
    }

    @Test
    fun getStepDuration() {

        val assemblySteps = instructionParser.parseToAssemblySteps()

        assertEquals(63, assemblySteps[0].duration)
    }
}
