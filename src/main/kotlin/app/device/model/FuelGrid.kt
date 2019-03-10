package app.device.model

import app.model.Array2D

class FuelGrid(private val serialNo: Int) {

    private val grid: Array2D<FuelCell> = Array2D(300, 300) { y: Int, x:Int -> FuelCell(x,y,serialNo)}

    init {

    }

    fun calculateCellPower() {

    }

    fun getCellPowerAt(x: Int, y: Int) : Int {
        val cell = grid[y][x]

        return cell.power
    }
}