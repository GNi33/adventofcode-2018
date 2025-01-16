package app.device.services

import app.device.groundscan.UndergroundMap
import app.device.model.groundscan.ClayVein

class GroundScanner(val clayVeins: List<ClayVein>) {

    val undergroundMap: UndergroundMap

    init {
        val bounds = determineBoundaries()
        undergroundMap = UndergroundMap(bounds["xMin"]!!, bounds["xMax"]!!, bounds["yMax"]!!)
    }

    fun determineBoundaries(): Map<String, Int> {

        val xMinCoord = clayVeins.minBy { it.x }
        val xMaxCoord = clayVeins.maxBy { it.x + (it.length - 1) }
        val yMin = 0
        val yMaxCoord = clayVeins.maxBy { it.y + (it.height - 1) }

        val xMax = xMaxCoord.x + (xMaxCoord.length - 1) + 1
        val yMax = yMaxCoord.y + (yMaxCoord.height - 1)

        return mapOf("xMin" to xMinCoord.x - 1, "xMax" to xMax, "yMin" to yMin, "yMax" to yMax)
    }
}
