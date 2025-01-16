package app.device.services

import app.device.model.groundscan.ClayVein
import app.util.IInputReader
import app.util.InputParser
import app.util.InputReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GroundScannerTest {

    val inputReader = InputReader(IInputReader.MODE.TEST)
    val testInput = inputReader.getFileForDay(17).readText()

    val inputParser = InputParser()
    val input = inputParser.parseInput(ClayVein::class, testInput, "\r\n")

    @Test
    fun inputTest() {
        assertEquals(8, input.size)
    }

    @Test
    fun determineBoundariesTest() {
        val groundScanner = GroundScanner(input)
        val bounds = groundScanner.determineBoundaries()

        assertEquals(494, bounds["xMin"])
        assertEquals(507, bounds["xMax"])
        assertEquals(0, bounds["yMin"])
        assertEquals(13, bounds["yMax"])
    }

    @Test
    fun mapTest() {

        val groundScanner = GroundScanner(input)
        val map = groundScanner.undergroundMap

        map.fillMap(input)
        map.printDimension()
        map.printMap()

    }
}
