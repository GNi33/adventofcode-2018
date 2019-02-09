package app.device.model

import app.model.Array2D
import app.model.Point

class DestinationMap(val coords: Map<String, Point>) {

    private val matrix : Array2D<String>

    init {
        val maxY = coords.maxBy { it.value.x }!!.value.x + 1
        val maxX = coords.maxBy { it.value.y }!!.value.y + 1

        matrix = Array2D(maxX, maxY, Array(maxX) { Array(maxY) {"."} })

        initializeDestinations()
    }

    private fun initializeDestinations() {
        for (destination in coords) {
            matrix[destination.value.y, destination.value.x] = destination.key
        }
    }

    fun print() {

        println("""
            Map Output

            x: ${matrix.xSize}
            y: ${matrix.ySize}

        """.trimIndent())

        val xRange = 0 until matrix.xSize

        for (line in xRange) {

            println(matrix[line].toList())
        }
    }

}