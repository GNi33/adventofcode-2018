package app.factory.model

import app.model.AbstractCharMap

class LumberCollectionArea(xSize: Int, ySize: Int): AbstractCharMap(xSize, ySize) {

    fun fillMap(mapInput: List<List<Char>>) {
        mapInput.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                arrayMap[y, x] = c
            }
        }
    }

    override fun printMap() {

        arrayMap.forEachRow { _, row ->
            row.forEach { c ->
                print(c)
            }
            println("")
        }

        println("")
    }

}
