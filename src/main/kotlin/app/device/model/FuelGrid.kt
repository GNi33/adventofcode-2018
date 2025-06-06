package app.device.model

import app.model.Array2D

private const val MAX_SIZE = 300

class FuelGrid(private val serialNo: Int) {

    private val width = MAX_SIZE
    private val height = MAX_SIZE

    private val grid: Array2D<FuelCell> = Array2D(width, height) { y: Int, x: Int -> FuelCell(x, y, serialNo) }

    fun calculateLargestTotal(size: Int): FuelCell {
        var largestTotal = 0
        var largestCell = grid[0][0]

        for (xi in 0 until width - size) {
            for (yi in 0 until height - size) {
                updateLargestTotalAndCell(xi, yi, size, largestTotal)?.let { (score, cell) ->
                    largestTotal = score
                    largestCell = cell
                }
            }
        }

        return largestCell
    }

    private fun updateLargestTotalAndCell(xi: Int, yi: Int, size: Int, currentLargestTotal: Int): Pair<Int, FuelCell>? {
        return try {
            val score = calculateTotalAt(xi, yi, size)
            if (score > currentLargestTotal) {
                Pair(score, grid[yi][xi])
            } else {
                null
            }
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    fun calculateLargestTotalOverSizes(): FuelCell {

        var largestCell = grid[0][0]
        var largestSize = 0
        var largestScore = 0

        for (size in 2..MAX_SIZE) {
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

    fun calculateTotalAt(x: Int, y: Int, size: Int): Int {

        if(x < 0 || y < 0) {
            error("Out of area")
        }

        if (x + (size - 1) > width - 1 || y + (size - 1) > height - 1) {
            error("Out of area")
        }

        var score = 0
        for (xi in 0 until size) {
            for (yi in 0 until size) {
                score += getCellPowerAt(x + xi, y + yi)
            }
        }

        return score
    }

    fun getCellPowerAt(x: Int, y: Int): Int {
        val cell = grid[y][x]

        return cell.power
    }
}
