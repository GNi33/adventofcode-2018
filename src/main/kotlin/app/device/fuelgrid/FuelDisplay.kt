package app.device.fuelgrid

import app.device.model.FuelCell
import app.device.model.FuelGrid

class FuelDisplay(private val serialNo: Int) {

    private val fuelGrid = FuelGrid(serialNo)

    fun getLargestTotal(): FuelCell {
        return fuelGrid.calculateLargestTotal()
    }

}