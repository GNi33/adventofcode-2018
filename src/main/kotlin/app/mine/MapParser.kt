package app.mine

import app.mine.model.Cart
import app.mine.model.Direction
import app.mine.model.Map
import java.lang.Exception

class MapParser(input:List<String>) {

    val map: Map
    val carts: MutableList<Cart> = mutableListOf()

    init {
        val xSize = input.size
        val ySize = input.maxByOrNull { it.count() }?.count() ?: throw Exception("Parsing went wrong")

        map = Map(xSize, ySize)

        input.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { charIndex, char ->

                var mapChar = char

                when (char) {
                    '>', '<' -> {
                        mapChar = '-'
                        carts.add(
                            Cart(rowIndex, charIndex, if(char == '<') Direction.WEST else Direction.EAST)
                        )

                    }
                    '^','v' -> {
                        mapChar = '|'
                        carts.add(
                            Cart(rowIndex, charIndex, if(char == 'v') Direction.SOUTH else Direction.NORTH)
                        )
                    }
                }

                map.arrayMap[rowIndex, charIndex] = mapChar
            }
        }

        carts.sortBy { it.xPos }
    }
}
