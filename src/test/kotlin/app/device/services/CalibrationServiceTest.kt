package app.device.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CalibrationServiceTest {

    @Test
    @DisplayName("1 + 1 + 1 = 3")
    fun calculatePositiveFrequencies() {
        val calibrationService = CalibrationService()
        val frequencyList = listOf("+1", "+1", "+1")

        assertEquals(3, calibrationService.calibrateFrequencies(frequencyList))
    }

    @Test
    @DisplayName("-1 - 2 - 3 = -6")
    fun calculateNegativeFrequencies() {
        val calibrationService = CalibrationService()
        val frequencyList = listOf("-1", "-2", "-3")

        assertEquals(-6, calibrationService.calibrateFrequencies(frequencyList))
    }

    @Test
    @DisplayName("1 + 1 - 2 = 0")
    fun calculateMixedFrequencies() {
        val calibrationService = CalibrationService()
        val frequencyList = listOf("+1", "+1", "-2")

        assertEquals(0, calibrationService.calibrateFrequencies(frequencyList))
    }

    @Test
    fun getDoubleFrequencies1() {
        val calibrationService = CalibrationService()
        val frequencyList = listOf("+1", "-1")

        assertEquals(0, calibrationService.findFirstDoubleOccurrence(frequencyList))
    }

    @Test
    fun getDoubleFrequencies2() {
        val calibrationService = CalibrationService()
        val frequencyList = listOf("+3", "+3", "+4", "-2", "-4")

        assertEquals(10, calibrationService.findFirstDoubleOccurrence(frequencyList))
    }

    @Test
    fun getDoubleFrequencies3() {
        val calibrationService = CalibrationService()

        val frequencyList = listOf("-6", "+3", "+8", "+5", "-6")

        assertEquals(5, calibrationService.findFirstDoubleOccurrence(frequencyList))
    }

    @Test
    fun getDoubleFrequencies4() {
        val calibrationService = CalibrationService()

        val frequencyList = listOf("+7", "+7", "-2", "-7", "-4")

        assertEquals(14, calibrationService.findFirstDoubleOccurrence(frequencyList))
    }
}
