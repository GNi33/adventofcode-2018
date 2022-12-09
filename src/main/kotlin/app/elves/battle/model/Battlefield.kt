package app.elves.battle.model

import app.model.AbstractCharMap

class Battlefield(xSize: Int, ySize: Int) : AbstractCharMap(xSize, ySize) {

    fun fetchMap(actors: List<Actor>, printOrder: Boolean = false): MutableList<String> {
        val output:MutableList<String> = mutableListOf()

        arrayMap.array.forEachIndexed { rowIdx, rowData ->
            val row = rowData.mapIndexed { colIdx, c ->

                val actorList = actors.filter { it.pos.y == rowIdx && it.pos.x == colIdx }

                when {
                    actorList.size > 1 -> {
                        // this should not happen, throw error here
                        "X"
                    }
                    actorList.size == 1 -> {
                        if (printOrder) {
                            actorList[0].actionOrder
                        } else {
                            actorList[0].getSymbol().toString()
                        }
                    }
                    else -> {
                        c.toString()
                    }
                }
            }

            output.add(row.joinToString(""))
        }

        return output
    }
}