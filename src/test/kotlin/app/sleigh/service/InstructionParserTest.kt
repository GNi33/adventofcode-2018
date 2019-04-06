package app.sleigh.service

import app.sleigh.model.AssemblyStep
import app.sleigh.model.IAssemblyStep
import app.sleigh.model.TestAssemblyStep
import app.util.IInputReader
import app.util.InputReader
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.log.EmptyLogger
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import java.util.NoSuchElementException

internal class InstructionParserTest : KoinTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String>
    private val instructionParser: InstructionParser

    init {
        testInput = inputReader.getDataForDay(7)
        instructionParser = InstructionParser(testInput)
    }

    @Before
    fun before() {
        startKoin(listOf(module {
            factory { (id: String) -> TestAssemblyStep(id) as IAssemblyStep }
        }), logger = EmptyLogger())
    }

    @After
    fun after() {
        stopKoin()
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

        val stepProcessor = TimedStepProcessor(linkedSteps, 2)
        val stepOrder = instructionParser.retrieveStepOrder(stepProcessor)

        assertEquals("CABFDE", stepOrder)
    }

    @Test
    fun retrieveTimedAssemblyDuration() {
        val linkedSteps = instructionParser.linkSteps()

        val stepProcessor = TimedStepProcessor(linkedSteps, 2)
        val assemblyDuration = instructionParser.retrieveAssemblyDuration(stepProcessor)

        assertEquals(15, assemblyDuration)
    }
}
