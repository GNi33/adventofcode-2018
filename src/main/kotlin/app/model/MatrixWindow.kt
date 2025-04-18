package app.model

import kotlin.math.floor

class MatrixWindow(size: Int) {

    private val values: Array2D<Char> = Array2D(size, size, Array(size) { Array(size) { ' ' } })
    private var baseMap: Array2D<Char>? = null
    var ctY: Int? = null
    var ctX: Int? = null

    init {
        require(size % 2 != 0) { "Size must be odd" }
    }

    fun getValue(y: Int, x: Int): Char {
        return values[y, x]
    }

    fun setValue(y: Int, x: Int, value: Char) {
        values[y, x] = value
    }

    fun setBaseMap(baseMap: Array2D<Char>) {
        this.baseMap = baseMap
    }

    fun clearBaseMap() {
        baseMap = null
    }

    fun setWindowAt(y: Int, x: Int) {
        ctX = x
        ctY = y

        val startingY = y - floor((values.ySize / 2).toDouble()).toInt()
        val startingX = x - floor((values.xSize / 2).toDouble()).toInt()

        baseMap?.let { map ->
            for (i in 0 until values.ySize) {
                for (j in 0 until values.xSize) {
                    setWindowValue(map, startingY + i, startingX + j, i, j)
                }
            }
        }
    }

    fun getCenter(): Char {
        check(ctX != null && ctY != null) { "Window not set" }

        return values[values.ySize / 2, values.xSize / 2]
    }

    fun countChar(char: Char, includeCenter: Boolean = false): Int {
        var count = values.array.sumOf { it.count { c -> c == char } }

        if(!includeCenter) {
            if (getCenter() == char) {
                count -= 1
            }

        }

        return count
    }

    private fun setWindowValue(map: Array2D<Char>, mapY: Int, mapX: Int, windowY: Int, windowX: Int) {
        if (isOutOfBounds(map, mapY, mapX)) {
            values[windowY, windowX] = ' '
        } else {
            values[windowY, windowX] = map[mapY, mapX]
        }
    }

    private fun isOutOfBounds(baseMap: Array2D<Char>, y: Int, x: Int): Boolean {
        return y < 0 || y >= baseMap.ySize || x < 0 || x >= baseMap.xSize
    }
}
