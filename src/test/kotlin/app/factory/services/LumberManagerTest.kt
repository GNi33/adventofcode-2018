package app.factory.services

import app.factory.model.LumberCollectionArea
import app.model.MatrixWindow
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LumberManagerTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val testInput: List<String> = inputReader.getDataForDay(18)

    @Test
    fun mapTest() {

        val ySize = testInput.size
        val xSize = testInput[0].length

        val lumberMap = LumberCollectionArea(ySize, xSize)
        lumberMap.fillMap(testInput.map { it.toList() })

        println(lumberMap.printMap())
    }

    @Test
    fun windowTest() {

        val ySize = testInput.size
        val xSize = testInput[0].length

        val lumberMap = LumberCollectionArea(ySize, xSize)
        lumberMap.fillMap(testInput.map { it.toList() })

        val slidingWindow = MatrixWindow(3)
        slidingWindow.setBaseMap(lumberMap.arrayMap)

        slidingWindow.setWindowAt(0,0)

        assertEquals(1, slidingWindow.countChar('#'))
        assertEquals(2, slidingWindow.countChar('.'))

        slidingWindow.setWindowAt(4,3)
        assertEquals(3, slidingWindow.countChar('#'))
        assertEquals(2, slidingWindow.countChar('|'))
        assertEquals(3, slidingWindow.countChar('|', true))
        assertEquals(3, slidingWindow.countChar('.'))
    }
}
