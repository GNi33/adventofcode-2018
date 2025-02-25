package app.model

abstract class AbstractCharMap(val xSize: Int, val ySize: Int) {
    open val arrayMap: Array2D<Char> = Array2D(xSize, ySize, Array(ySize) { Array(xSize) { ' ' } })

    fun getMapTileAt(y: Int, x: Int): Char {
        return arrayMap[y, x]
    }

    open fun printMap() {

        arrayMap.forEachRow {_, row ->
            println(
                row.map { c ->
                    c.toString()
                }
            )
        }

        println("")
    }

    fun printDimension() {
        println("xSize: ${arrayMap.array[0].size}, ySize: ${arrayMap.array.size}")
    }

    fun clearMap() {
        arrayMap.forEachIndexed { y, x, _ ->
            arrayMap[y, x] = ' '
        }
    }
}
