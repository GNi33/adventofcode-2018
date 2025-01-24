package app.device.groundscan

import app.device.model.groundscan.ClayVein
import app.model.AbstractCharMap
import app.model.Array2D
import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.log10

class UndergroundMap(val xMin: Int, val xMax: Int, val yMax: Int): AbstractCharMap((xMax - xMin)+1, yMax+1) {
    override val arrayMap: Array2D<Char> = Array2D(xSize, ySize, Array(ySize) { Array(xSize) { '.' } })

    fun fillMap(clayVeins: List<ClayVein>) {
        clayVeins.forEach { clayVein ->
            clayVein.getPoints().forEach { point ->
                arrayMap[point.y, point.x - xMin] = '#'
            }
        }

        arrayMap[0, 500 - xMin] = '+'
    }

    fun printMap(maxY: Int, minY: Int = 0) {

        val xDigits = if (xMax == 0) 1 else floor(log10(xMax.toDouble().absoluteValue)).toInt() + 1
        val yDigits = if (yMax == 0) 1 else floor(log10(yMax.toDouble().absoluteValue)).toInt() + 1

        for (i in 0 until xDigits) {
            print(" ".repeat(yDigits) + " ")
            for (j in 0 until xSize) {
                print((xMin + j).toString()[i])
            }
            println("")
        }

        arrayMap.forEachRow { idx, row ->

            if(idx < minY) {
                return@forEachRow
            }

            print(idx.toString().padStart(yDigits) + " ")

            row.forEach { c ->
                print(c)
            }

            println("")

            if(maxY != 0 && idx == maxY) {
                return
            }
        }

        println("")
    }

}
