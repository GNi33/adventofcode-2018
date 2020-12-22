package app.device.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FuelGridTest {

    @Test
    fun calculateCellPower() {

        val fuelGrid57 = FuelGrid(57)
        val fuelGrid8 = FuelGrid(8)
        val fuelGrid39 = FuelGrid(39)
        val fuelGrid71 = FuelGrid(71)

        assertEquals(4, fuelGrid8.getCellPowerAt(3, 5))
        assertEquals(-5, fuelGrid57.getCellPowerAt(122, 79))
        assertEquals(0, fuelGrid39.getCellPowerAt(217, 196))
        assertEquals(4, fuelGrid71.getCellPowerAt(101, 153))
    }

    @Test
    fun getLargestTotal() {
        val fuelGrid18 = FuelGrid(18)
        val fuelGrid42 = FuelGrid(42)

        val largestCell18 = fuelGrid18.calculateLargestTotal(18)
        val largestCell42 = fuelGrid42.calculateLargestTotal(42)

        assertEquals(33, largestCell18.x)
        assertEquals(45, largestCell18.y)

        assertEquals(21, largestCell42.x)
        assertEquals(61, largestCell42.y)
    }
}