package app.device.skywatch

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LightParserTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String>
    private val lightParser: LightParser

    init {
        testInput = inputReader.getDataForDay(10)
        lightParser = LightParser(testInput)
    }

    @Test
    fun getLights() {

        assertEquals(Pair(9, 1), lightParser.lights[0].position)
        assertEquals(Pair(3, -2), lightParser.lights[2].position)
        assertEquals(Pair(-1, 1), lightParser.lights[2].velocity)
        assertEquals(31, lightParser.lights.size)
    }
}
