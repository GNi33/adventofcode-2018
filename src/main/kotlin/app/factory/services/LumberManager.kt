package app.factory.services

import app.factory.model.LumberCollectionArea
import app.model.Array2D
import app.model.MatrixWindow

class LumberManager(xSize: Int, ySize: Int, windowSize: Int) {
    val lumberCollectionArea = LumberCollectionArea(xSize, ySize)
    val window = MatrixWindow(windowSize)

    constructor(xSize: Int, ySize: Int, windowSize: Int, mapInput: List<List<Char>>) : this(xSize, ySize, windowSize) {
        lumberCollectionArea.fillMap(mapInput)
        window.setBaseMap(lumberCollectionArea.arrayMap)
    }

    fun fillMap(mapInput: List<List<Char>>) {
        lumberCollectionArea.fillMap(mapInput)
    }

    fun printMap() {
        lumberCollectionArea.printMap()
    }

    fun passMinute() {

        val newMap = Array2D(lumberCollectionArea.arrayMap.xSize, lumberCollectionArea.arrayMap.ySize, Array(lumberCollectionArea.arrayMap.ySize) { Array(lumberCollectionArea.arrayMap.xSize) { ' ' } })
        newMap.forEachIndexed { y, x, _ ->
            newMap[y, x] = lumberCollectionArea.arrayMap[y, x]
        }

        window.setBaseMap(lumberCollectionArea.arrayMap)

        lumberCollectionArea.arrayMap.forEachIndexed { y, x, c ->
            window.setWindowAt(y, x)
            newMap[y, x] = when (c) {
                '.' -> if (window.countChar('|') >= 3) '|' else '.'
                '|' -> if (window.countChar('#') >= 3) '#' else '|'
                '#' -> if (window.countChar('#') >= 1 && window.countChar('|') >= 1) '#' else '.'
                else -> c
            }
        }

        lumberCollectionArea.arrayMap.forEachIndexed { y, x, _ ->
            lumberCollectionArea.arrayMap[y, x] = newMap[y, x]
        }
    }

    fun getWoodAcres(): Int {
        return countChar('|')
    }

    fun getLumberyards(): Int {
        return countChar('#')
    }

    fun countChar(c: Char): Int {
        var ct = 0;
        lumberCollectionArea.arrayMap.forEachIndexed { _, _, it ->
            if (it == c) {
                ct++
            }
        }

        return ct
    }

}
