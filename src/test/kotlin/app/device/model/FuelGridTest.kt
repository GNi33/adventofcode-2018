package app.device.model

import app.device.license.LicenseParser
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FuelGridTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val input = inputReader.getDataForDay(11)

    @Test
    fun calculateCellPower() {

        val fuelGrid57 = FuelGrid(57)
        val fuelGrid8 = FuelGrid(8)
        val fuelGrid39 = FuelGrid(39)
        val fuelGrid71 = FuelGrid(71)

        assertEquals(4, fuelGrid8.getCellPowerAt(3,5))
        assertEquals(-5, fuelGrid57.getCellPowerAt(122,79))
        assertEquals(0, fuelGrid39.getCellPowerAt(217,196))
        assertEquals(4, fuelGrid71.getCellPowerAt(101,153))
    }
}