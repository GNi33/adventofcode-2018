package app.model

abstract class AbstractCharMap(val xSize: Int, val ySize: Int) {
    val arrayMap: Array2D<Char> = Array2D(xSize, ySize, Array(xSize) { Array(ySize) { ' ' } })

    fun printMap() {

        arrayMap.array.forEach {
            println(
                it.map { c ->
                    c.toString()
                }
            )
        }

        println("")
    }

    fun clearMap() {
        arrayMap.forEachIndexed { x, y, _ ->
            arrayMap[x, y] = ' '
        }
    }
}