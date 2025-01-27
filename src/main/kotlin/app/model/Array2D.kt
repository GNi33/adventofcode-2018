package app.model

const val DEFAULT_SIZE = 1000

class Array2D<T> (val xSize: Int, val ySize: Int, var array: Array<Array<T>>) {

    operator fun get(y: Int): Array<T> {
        return array[y]
    }

    operator fun get(y: Int, x: Int): T {
        return array[y][x]
    }

    operator fun set(y: Int, x: Int, t: T) {
        array[y][x] = t
    }

    inline fun forEachRow(operation: (Int, Array<T>) -> Unit) {
        array.forEachIndexed { i: Int, ts: Array<T> -> operation.invoke(i, ts) }
    }

    inline fun forEach(operation: (T) -> Unit) {
        array.forEach { it.forEach { operation.invoke(it) } }
    }

    inline fun forEachIndexed(operation: (x: Int, y: Int, T) -> Unit) {
        array.forEachIndexed { x, p -> p.forEachIndexed { y, t -> operation.invoke(x, y, t) } }
    }

    companion object {
        inline operator fun <reified T> invoke() = Array2D(
            DEFAULT_SIZE,
            DEFAULT_SIZE,
            Array(DEFAULT_SIZE) { arrayOfNulls<T>(DEFAULT_SIZE) }
        )

        inline operator fun <reified T> invoke(yHeight: Int, xWidth: Int, operator: (Int, Int) -> (T)): Array2D<T> {
            val array = Array(yHeight) { x ->
                Array(xWidth) { it1 -> operator(x, it1) }
            }
            return Array2D(yHeight, xWidth, array)
        }
    }
}
