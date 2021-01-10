package app.mine

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest

internal class CartSimulationTest : KoinTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(13)

    @Test
    fun testSim() {
        val sim = CartSimulation(testInput, mapOf("printSimSteps" to true))

        sim.runSimulation(5000)

        val crashes = sim.getCrashList()
        val firstCrash = crashes[0]

        assertEquals(7, firstCrash.first)
        assertEquals(3, firstCrash.second)
    }
}