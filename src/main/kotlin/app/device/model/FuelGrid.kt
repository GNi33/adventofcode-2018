package app.device.model

import app.model.Array2D

class FuelGrid(private val serialNo: Int) {

    private val width = 300
    private val height = 300

    private val grid: Array2D<FuelCell> = Array2D(width, height) { y: Int, x:Int -> FuelCell(x,y,serialNo)}

    fun calculateLargestTotal(size: Int) : FuelCell {

        var largestTotal = 0
        var largestCell = grid[0][0]

        for (xi in 0 until width - size) {
            for (yi in 0 until height - size) {
                try {
                    val score = calculateTotalAt(xi, yi, size)

                    if (score > largestTotal) {
                        largestTotal = score
                        largestCell = grid[yi][xi]
                    }
                } catch (e : Exception) {
                    println(e.message)
                }
            }
        }

        return largestCell
    }

    fun calculateLargestTotalOverSizes(): FuelCell {

        var largestCell = grid[0][0]
        var largestSize = 0
        var largestScore = 0

        for (size in 2..300) {
            val cell = calculateLargestTotal(size)
            val cellTotal = calculateTotalAt(cell.x, cell.y, size)

            if (cellTotal > largestScore) {
                largestScore = cellTotal
                largestCell = cell
                largestSize = size
            }
        }


        println(largestSize)
        return largestCell
    }

    fun calculateTotalAt(x: Int, y: Int, size: Int) : Int {
        if (x + (size-1) > width - 1 || y + (size-1) > height - 1 || x < 0 || y < 0) {
            throw Exception("Out of area")
        }

        var score = 0
        for (xi in 0 until size) {
            for (yi in 0 until size) {
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