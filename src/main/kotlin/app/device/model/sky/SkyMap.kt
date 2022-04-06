package app.device.model.sky

import app.model.Array2D
import kotlin.math.abs

class SkyMap(private val boundaries: List<Int>) {

    private val xDim: Int
    private val yDim: Int
    private val map: Array2D<Char>

    init {
        val (tmpXDim, tmpYDim) = getDimensions()

        xDim = tmpXDim
        yDim = tmpYDim

        map = Array2D(yDim, xDim, Array(yDim) { Array(xDim) { ' ' } })
    }

    fun setLights(lights: List<SkyLight>) {
        lights.forEach {
            setLight(it)
        }
    }

    fun setLight(light: SkyLight) {

        val x = light.currentPosition.first + (xDim / 2)
        val y = light.currentPosition.second + (yDim / 2)

        if (x in 0 until xDim && y in 0 until yDim) {
            map[y, x] = '*'
        }
    }

    fun clearSky() {
        map.forEachIndexed { x, y, _ ->
            map[x, y] = ' '
        }
    }

    fun printSky() {

        map.array.forEach {
            println(
                it.map { c ->
                    c.toString()
                }
            )
        }

        println("")
    }

    // todo this shouldn't be in here
    fun getBoundaries(positions: List<SkyLight>): List<Int> {
        val minXDim = positions.minByOrNull {
            it.currentPosition.first
        }!!.currentPosition.first

        val maxXDim = positions.maxByOrNull {
            it.currentPosition.first
        }!!.currentPosition.first

        val minYDim = positions.minByOrNull {
            it.currentPosition.second
        }!!.currentPosition.second

        val maxYDim = positions.maxByOrNull {
            it.currentPosition.second
        }!!.currentPosition.second

        return listOf(minXDim, maxXDim, minYDim, maxYDim)
    }

    private fun getDimensions(): Pair<Int, Int> {

        val (minXDim, maxXDim, minYDim, maxYDim) = boundaries

        val xDim = if (abs(minXDim) > abs(maxXDim)) (abs(minXDim) * 2) + 2 else (abs(maxXDim) * 2) + 2
        val yDim = if (abs(minYDim) > abs(maxYDim)) (abs(minYDim) * 2) + 2 else (abs(maxYDim) * 2) + 2

        return Pair(xDim, yDim)
    }
}
