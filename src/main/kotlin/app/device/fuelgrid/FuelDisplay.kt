package app.device.fuelgrid

import app.device.model.FuelCell
import app.device.model.FuelGrid

class FuelDisplay(serialNo: Int) {

    private val fuelGrid = FuelGrid(serialNo)

    fun getLargestTotal(size: Int): FuelCell {
        return fuelGrid.calculateLargestTotal(size)
    }

    fun getLargestTotalOverSizes(): FuelCell {
        return fuelGrid.calculateLargestTotalOverSizes()
    }
}
