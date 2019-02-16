package app.sleigh.service

import app.sleigh.model.AssemblyStep
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

        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = StepProcessor(linkedSteps)
        val stepOrder = instructionParser.retrieveStepOrder(stepProcessor)

        assertEquals("CABDFE", stepOrder)
    }

    @Test
    fun getStepDuration() {

        val assemblyStepA = AssemblyStep("A")
        val assemblyStepC = AssemblyStep("C")
        val assemblyStepZ = AssemblyStep("Z")

        assertEquals(61, assemblyStepA.duration)
        assertEquals(63, assemblyStepC.duration)
        assertEquals(86, assemblyStepZ.duration)
    }

    @Test
    fun retrieveTimedStepOrder() {
        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = TimedStepProcessor(linkedSteps,2)
        val stepOrder = instructionParser.retrieveStepOrder(stepProcessor)

        assertEquals("CABFDE", stepOrder)
    }

    @Test
    fun retrieveTimedAssemblyDuration() {
        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = TimedStepProcessor(linkedSteps,2)
        val assemblyDuration = instructionParser.retrieveAssemblyDuration(stepProcessor)

        assertEquals(15, assemblyDuration)
    }
}
