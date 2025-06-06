package app.device.model

import app.model.Array2D
import app.model.Point
import kotlin.math.abs

class DestinationMap(private val coords: Map<String, Point>) {

    private val matrix: Array2D<String>

    init {
        // +2 because we want to account for line/col 0 as well as 1 extra padding
        val maxY = coords.maxByOrNull { it.value.x }!!.value.x + 2
        val maxX = coords.maxByOrNull { it.value.y }!!.value.y + 2

        matrix = Array2D(maxX, maxY, Array(maxX) { Array(maxY) { "." } })

        initializeDestinations()
    }

    fun setAreas() {

        val lines = 0 until getHeight()
        val cols = 0 until getWidth()

        for (line in lines) {
            for (col in cols) {
                val closestDest = getClosestDestination(Point(col, line))

                if (matrix[line, col] == ".") {
                    matrix[line, col] = closestDest.lowercase()
                }
            }
        }
    }

    fun setCloseAreas(limit: Int) {
        val lines = 0 until getHeight()
        val cols = 0 until getWidth()

        for (line in lines) {
            for (col in cols) {
                val closestDest = getCombinedDistance(col, line)

                if (closestDest < limit) {
                    matrix[line, col] = "#"
                }
            }
        }
    }

    private fun initializeDestinations() {
        for (destination in coords) {
            matrix[destination.value.y, destination.value.x] = destination.key
        }
    }

    fun getCombinedDistance(x: Int, y: Int): Int {
        return getCombinedDistance(Point(x, y))
    }

    fun getCombinedDistance(sourceCoordinate: Point): Int {
        val distances = getDistances(sourceCoordinate)

        return distances.values.reduce { acc, elem -> acc + elem }
    }


    fun getClosestDestination(sourceCoordinate: Point): String {

        val distances = getDistances(sourceCoordinate)

        val minDist = distances.minByOrNull {
            it.value
        } ?: return "."

        val minDists = distances.filter {
            it.value == minDist.value
        }

        return if(minDists.size > 1) "." else minDist.key
    }

    fun getDistances(sourceCoordinate: Point): Map<String, Int> {
        return coords.map {
            it.key to calculateDistance(sourceCoordinate, it.key)
        }.toMap()
    }

    private fun calculateDistance(sourceCoordinate: Point, targetCoordinate: String): Int {
        return calculateDistance(sourceCoordinate, coords.getValue(targetCoordinate))
    }

    private fun calculateDistance(sourceCoordinate: Point, targetCoordinate: Point): Int {
        val x = abs(sourceCoordinate.x - targetCoordinate.x)
        val y = abs(sourceCoordinate.y - targetCoordinate.y)
        return x + y
    }

    fun getHeight(): Int {
        return matrix.xSize
    }

    fun getWidth(): Int {
        return matrix.ySize
    }

    fun getEdgeValues(): List<Array<String>> {

        val firstLine = getFirstLine()
        val lastLine = getLastLine()

        val firstCol = getFirstColumn()
        val lastCol = getLastColumn()

        return listOf(firstLine, lastLine, firstCol, lastCol)
    }

    private fun getFirstColumn(): Array<String> {
        return getColumn(0)
    }

    private fun getLastColumn(): Array<String> {
        return getColumn(getWidth() - 1)
    }

    private fun getColumn(colNum: Int): Array<String> {
        val lines = 0 until getHeight()
        val columnValues = Array(getHeight()) { "" }

        for (line in lines) {
            columnValues[line] = matrix[line][colNum]
        }

        return columnValues
    }

    private fun getFirstLine(): Array<String> {
        return getLine(0)
    }

    private fun getLastLine(): Array<String> {
        return getLine(getHeight() - 1)
    }

    fun getLine(lineNum: Int): Array<String> {
        return matrix[lineNum]
    }

    fun getValue(line: Int, col: Int): String {
        return matrix[line, col]
    }
}
