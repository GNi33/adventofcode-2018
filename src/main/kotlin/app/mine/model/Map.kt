package app.mine.model

import app.model.AbstractCharMap

class Map(xSize: Int, ySize: Int) : AbstractCharMap(xSize, ySize) {

    fun printMap(carts: List<Cart>) {
        arrayMap.array.forEachIndexed { rowIdx, rowData ->
            val row = rowData.mapIndexed { colIdx, c ->

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
