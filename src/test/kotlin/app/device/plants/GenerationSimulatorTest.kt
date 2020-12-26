package app.device.plants

import app.days.DayConsts
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class GenerationSimulatorTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(DayConsts.DAY_12)
    private val plantPotSimulator = PlantPotSimulator(testInput)

    @Test
    fun generationsTest() {
        val generations = plantPotSimulator.simulateGenerations(20)

        plantPotSimulator.printGenerations(generations)

        val plantCount = plantPotSimulator.countPlantPotNumbersOfLastGen(generations)

        assertEquals(325, plantCount)
    }
}