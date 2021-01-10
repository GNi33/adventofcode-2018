package app.mine

import app.mine.model.Direction
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest

internal class MapParserTest : KoinTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(13)

    private val mapParser: MapParser = MapParser(testInput)

    @Test
    fun testMapDimensions() {
        val map = mapParser.map

        assertEquals(6, map.xSize)
        assertEquals(13, map.ySize)
    }

    @Test
    fun testCartCount() {
        val carts = mapParser.carts

        assertEquals(2, carts.size)
    }

    @Test
    fun testCarts() {
        val carts = mapParser.carts

        val cart01 = carts[0]

        assertEquals(Direction.EAST, cart01.direction)
        assertEquals(0, cart01.xPos)
        assertEquals(2, cart01.yPos)

        val cart02 = carts[1]

        assertEquals(Direction.SOUTH, cart02.direction)
        assertEquals(3, cart02.xPos)
        assertEquals(9, cart02.yPos)
    }
}