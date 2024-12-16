package app.mine

import app.mine.model.Direction
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MapParserTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(13)
    private val testInput2: List<String> = inputReader.getDataForDay(13, 2)

    private val mapParser: MapParser = MapParser(testInput)
    private val mapParser2: MapParser = MapParser(testInput2)

    @Test
    fun testMapDimensions() {
        val map = mapParser.map
        val map2 = mapParser2.map

        assertEquals(6, map.xSize)
        assertEquals(13, map.ySize)

        assertEquals(7, map2.xSize)
        assertEquals(7, map2.ySize)
    }

    @Test
    fun testCartCount() {
        val carts = mapParser.carts
        val carts2 = mapParser2.carts

        assertEquals(2, carts.size)
        assertEquals(9, carts2.size)
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
