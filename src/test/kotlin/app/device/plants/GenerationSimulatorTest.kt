package app.device.plants

import app.days.DayConsts
import app.device.model.FuelGrid
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GenerationSimulatorTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(DayConsts.DAY_12)
    private val plantPotSimulator = PlantPotSimulator(testInput)

    @Test
    fun generationsTest() {
        plantPotSimulator.simulateGenerations(20)
    }
}