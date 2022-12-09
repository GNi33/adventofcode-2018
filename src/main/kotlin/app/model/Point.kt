package app.model

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int = when {
        this.y != other.y -> this.y compareTo other.y
        this.x != other.x -> this.x compareTo other.x
        else -> 0
    }
}
