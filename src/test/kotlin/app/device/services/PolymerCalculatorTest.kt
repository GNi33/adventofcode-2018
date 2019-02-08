package app.device.services

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PolymerCalculatorTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)

    @Test
    fun processPolymerReaction() {
        val polymerCalc = PolymerCalculator()
        val expectedResult = "dabCBAcaDA"

        val inputFromFile = inputReader.getDataFromFile("polymer.txt")
        val input = inputFromFile.first()

        assertEquals(expectedResult, polymerCalc.processPolymerReaction(input))
    }


    @Test
    fun getProcessedPolymerLength() {
    }
}