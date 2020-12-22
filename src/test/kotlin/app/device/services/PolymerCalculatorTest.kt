package app.device.services

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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

        val polymerCalc = PolymerCalculator()
        val expectedResult = 10

        val inputFromFile = inputReader.getDataFromFile("polymer.txt")
        val input = inputFromFile.first()

        assertEquals(expectedResult, polymerCalc.getProcessedPolymerLength(input))
    }

    @Test
    fun getShortestPolymerLength() {

        val polymerCalc = PolymerCalculator()
        val expectedResult = 4

        val inputFromFile = inputReader.getDataFromFile("polymer.txt")
        val input = inputFromFile.first()

        assertEquals(expectedResult, polymerCalc.getShortestPolymerLength(input))
    }
}
