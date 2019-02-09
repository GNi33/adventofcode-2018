package app.device.services

import app.model.Point
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DestinationMapperTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)

    @Test
    fun getLargestAreaSize() {
        val testInput = inputReader.getDataFromFile("coordinates.txt")

        val destMapper = DestinationMapper(testInput)

        destMapper.getLargestAreaSize()
    }
}