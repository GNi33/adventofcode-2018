package app.device.model.groundscan

import app.model.DeserializableInputInterface
import app.model.Point

class ClayVein: DeserializableInputInterface {

    var x: Int = 0
    var y: Int = 0
    var length: Int = 0
    var height: Int = 0

    fun getPoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (i in x until x + length) {
            for (j in y until y + height) {
                points.add(Point(i, j))
            }
        }
        return points
    }

    override fun deserializeInput(input: String) {

        val regex = Regex("""[xy]=(\d+), [xy]=(\d+)..(\d+)""")
        val match = regex.find(input) ?: throw IllegalArgumentException("Invalid input")

        val (first, second, third) = match.destructured
        if (input.startsWith("x")) {
            x = first.toInt()
            y = second.toInt()
            length = 1
            height = third.toInt() - second.toInt() + 1
        } else {
            x = second.toInt()
            y = first.toInt()
            length = third.toInt() - second.toInt() + 1
            height = 1
        }
    }
}
