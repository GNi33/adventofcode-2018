package app.device.skywatch

import app.device.model.sky.SkyLight
import app.device.model.sky.SkyMap
import java.util.ArrayDeque
import java.util.Deque

class LightsInTheSky(input: List<String>) {

    private val parser = LightParser(input)
    private val originalBoundaries = getBoundaries(parser.lights)

    fun simulateSky() {

        var smallestLightDistance = Int.MAX_VALUE
        var smallestStep = 0
        var stepNum = 0

        val steps: Deque<List<SkyLight>> = ArrayDeque()
        steps.addFirst(parser.lights)
        steps.addLast(parser.lights)

        while (getNumberOfLightsInBounds(steps.first) == parser.lights.size) {

            stepNum += 1

            val step = makeStep(steps.first)
            val dist = getLightDistances(step)

            if (dist < smallestLightDistance) {
                smallestLightDistance = dist
                smallestStep = stepNum
                steps.removeLast()
                steps.addLast(step)
            }

            steps.removeFirst()
            steps.addFirst(step)
        }

        println(smallestLightDistance)
        println(smallestStep)

        val map = SkyMap(getBoundaries(steps.last))
        map.clearSky()
        map.setLights(steps.last)
        map.printSky()
    }

    private fun makeStep(lights: List<SkyLight>): List<SkyLight> {
        return lights.map {
            it.takeStep()
        }
    }

    private fun getBoundaries(positions: List<SkyLight>): List<Int> {
        val minXDim = positions.minBy {
            it.currentPosition.first
        }!!.currentPosition.first

        val maxXDim = positions.maxBy {
            it.currentPosition.first
        }!!.currentPosition.first

        val minYDim = positions.minBy {
            it.currentPosition.second
        }!!.currentPosition.second

        val maxYDim = positions.maxBy {
            it.currentPosition.second
        }!!.currentPosition.second

        return listOf(minXDim, maxXDim, minYDim, maxYDim)
    }

    private fun getLightDistances(lights: List<SkyLight>): Int {
        val (minXDim, maxXDim, minYDim, maxYDim) = getBoundaries(lights)
        return maxXDim - minXDim + maxYDim - minYDim
    }

    private fun getNumberOfLightsInBounds(lights: List<SkyLight>): Int {
        val (minXDim, maxXDim, minYDim, maxYDim) = originalBoundaries

        return lights.fold(0) { acc, skyLight ->

            val x = skyLight.currentPosition.first
            val y = skyLight.currentPosition.second

            if (x in (minXDim..maxXDim) && y in (minYDim..maxYDim)) acc + 1 else acc
        }
    }
}