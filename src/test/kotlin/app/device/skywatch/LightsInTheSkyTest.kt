package app.device.skywatch

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LightsInTheSkyTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val sky: LightsInTheSky

    init {
        val input = inputReader.getDataForDay(10)
        sky = LightsInTheSky(input)
    }

    @Test
    fun simulateSky() {
        // Not really a test for now but oh well
        sky.simulateSky()
    }
}