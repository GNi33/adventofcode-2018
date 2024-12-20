package app.model

const val DEFAULT_SIZE = 1000

class Array2D<T> (val xSize: Int, val ySize: Int, var array: Array<Array<T>>) {

    operator fun get(x: Int): Array<T> {
        return array[x]
    }

    operator fun get(x: Int, y: Int): T {
        return array[x][y]
    }

    operator fun set(x: Int, y: Int, t: T) {
        array[x][y] = t
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

        inline operator fun <reified T> invoke(xWidth: Int, yWidth: Int, operator: (Int, Int) -> (T)): Array2D<T> {
            val array = Array(xWidth) {
                val x = it
                Array(yWidth) { it1 -> operator(x, it1) }
            }
            return Array2D(xWidth, yWidth, array)
        }
    }
}
