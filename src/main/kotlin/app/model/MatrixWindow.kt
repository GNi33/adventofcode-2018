package app.model

import kotlin.math.floor

class MatrixWindow(size: Int) {

    private val values: Array2D<Char> = Array2D(size, size, Array(size) { Array(size) { ' ' } })
    private var baseMap: Array2D<Char>? = null
    var ctY: Int? = null
    var ctX: Int? = null

    init {
        if(size % 2 == 0) {
            throw IllegalArgumentException("Size must be odd")
        }
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

        baseMap?.let {
            for (i in 0 until values.ySize) {
                for (j in 0 until values.xSize) {
                    if(startingY + i < 0 || startingY + i >= it.ySize || startingX + j < 0 || startingX + j >= it.xSize) {
                        values[i, j] = ' '
                        continue
                    }

                    values[i, j] = it[startingY + i, startingX + j]
                }
            }
        }
    }

    fun countChar(char: Char, includeCenter: Boolean = false): Int {

        if (ctX == null || ctY == null) {
            throw IllegalStateException("Window not set")
        }

        var count = values.array.sumOf { it.count { c -> c == char } }

        if(!includeCenter) {
            if (values[values.ySize / 2,values.xSize / 2] == char) {
                count -= 1
            }

        }

        return count
    }


}
