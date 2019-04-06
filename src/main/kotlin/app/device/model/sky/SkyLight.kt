package app.device.model.sky

data class SkyLight(val position: Pair<Int, Int>, val velocity: Pair<Int, Int>) {

    var currentPosition: Pair<Int, Int> = position

    companion object {

        private val pattern = """<\s*(-?\d+),\s*(-?\d+)>""".toRegex()

        fun create(inputLine: String): SkyLight {

            var matchResult = pattern.find(inputLine)

            val (posX, posY) = matchResult!!.destructured

            matchResult = matchResult.next()

            val (velX, velY) = matchResult!!.destructured

            return SkyLight(Pair(posX.toInt(), posY.toInt()), Pair(velX.toInt(), velY.toInt()))
        }
    }

    fun takeStep(): SkyLight {

        val newPosition = Pair(currentPosition.first + velocity.first, currentPosition.second + velocity.second)

        return this.copy(position = newPosition)
    }
}