package app.device.services

import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DestinationMapperTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)

    @Test
    fun getLargestAreaSize() {
        val testInput = inputReader.getDataFromFile("coordinates.txt")

        val destMapper = DestinationMapper(testInput)
        destMapper.determineAreas()

        assertEquals(listOf("AD", "AE"), destMapper.getFiniteAreas())
        assertEquals(17, destMapper.getLargestAreaSize())
    }

    @Test
    fun getCloseAreaSize() {
        val testInput = inputReader.getDataFromFile("coordinates.txt")

        val destMapper = DestinationMapper(testInput)
        destMapper.determineCloseAreas(32)
        assertEquals(16, destMapper.getAreaSize("#"))
    }
}
