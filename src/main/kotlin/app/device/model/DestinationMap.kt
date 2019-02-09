package app.device.model

import app.model.Array2D
import app.model.Point
import kotlin.math.abs

class DestinationMap(private val coords: Map<String, Point>) {

    private val matrix: Array2D<String>

    init {
        // +2 because we want to account for line/col 0 as well as 1 extra padding
        val maxY = coords.maxBy { it.value.x }!!.value.x + 2
        val maxX = coords.maxBy { it.value.y }!!.value.y + 2

        matrix = Array2D(maxX, maxY, Array(maxX) { Array(maxY) { "." } })

        initializeDestinations()
    }

    fun setAreas() {

        val lines = 0 until getHeight()
        val cols = 0 until getWidth()

        for (line in lines) {
            for (col in cols) {
                val closestDest = getClosestDestination(col, line)

                if (matrix[line, col] == ".") {
                    matrix[line, col] = closestDest.toLowerCase()
                }
            }
        }
    }

    private fun initializeDestinations() {
        for (destination in coords) {
            matrix[destination.value.y, destination.value.x] = destination.key
        }
    }

    fun getClosestDestination(x: Int, y: Int): String {
        return getClosestDestination(Point(x, y))
    }

    fun getClosestDestination(sourceCoordinate: Point): String {

        val distances = getDistances(sourceCoordinate)

        val minDist = distances.minBy {
            it.value
        } ?: return "."

        val minDists = distances.filter {
            it.value == minDist.value
        }

        if (minDists.size > 1) {
            return "."
        }

        return minDist.key
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

    private fun getLine(lineNum: Int): Array<String> {
        return matrix[lineNum]
    }

    fun print() {

        println(
            """
            Map Output
                Width: ${getWidth()}
                Height: ${getHeight()}

        """.trimIndent()
        )

        val xRange = 0 until getHeight()

        for (line in xRange) {
            println(matrix[line].toList())
        }
    }

    fun getValue(line: Int, col: Int): String {
        return matrix[line, col]
    }

}