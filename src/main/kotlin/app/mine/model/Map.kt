package app.mine.model

import app.model.AbstractCharMap

class Map(xSize: Int, ySize: Int) : AbstractCharMap(xSize, ySize) {

    fun getMapTileAt(x: Int, y: Int): Char {
        return arrayMap[x, y]
    }

    fun printMap(carts: List<Cart>) {
        arrayMap.array.forEachIndexed { rowIdx, it ->
            val row = it.mapIndexed { colIdx, c ->

                val cartL = carts.filter { it.xPos == rowIdx && it.yPos == colIdx }

                when {
                    cartL.size > 1 -> {
                        "X"
                    }
                    cartL.size == 1 -> {
                        cartL[0].getSymbol().toString()
                    }
                    else -> {
                        c.toString()
                    }
                }
            }

            println(row.joinToString(""))
        }
    }

}