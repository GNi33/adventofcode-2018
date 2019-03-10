package app.device.model

import app.model.Array2D

class FuelGrid(private val serialNo: Int) {

    val width = 300
    val height = 300

    private val grid: Array2D<FuelCell> = Array2D(width, height) { y: Int, x:Int -> FuelCell(x,y,serialNo)}

    fun calculateLargestTotal() : FuelCell {

        var largestTotal = 0
        var largestCell = grid[0][0]

        for (xi in 0 until width) {
            for (yi in 0 until height) {
                try {
                    val score = calculateTotalAt(xi, yi)

                    if (score > largestTotal) {
                        largestTotal = score
                        largestCell = grid[yi][xi]
                    }
                } catch (e : Exception) {
                    // uhm...
                }
            }
        }

        return largestCell
    }

    fun calculateTotalAt(x: Int, y: Int) : Int {
        if (x + 2 > width || y + 2 > height || x < 0 || y < 0) {
            throw Exception("Out of area")
        }

        var score = 0
        for (xi in 0 until 3) {
            for (yi in 0 until 3) {
                score += getCellPowerAt(x + xi, y + yi)
            }
        }

        return score
    }

    fun getCellPowerAt(x: Int, y: Int) : Int {
        val cell = grid[y][x]

        return cell.power
    }
}