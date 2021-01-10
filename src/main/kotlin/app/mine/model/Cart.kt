package app.mine.model

import org.reflections.vfs.Vfs

class Cart(x: Int, y: Int, initialDirection: Direction) {

    private var crossingCount: Int = 0

    var xPos: Int = x
    var yPos: Int = y
    var direction: Direction = initialDirection

    fun makeMove(map: Map) {

        val mapTile = map.getMapTileAt(xPos, yPos)

        direction = getNewDirection(mapTile)

        when (direction) {
            Direction.SOUTH -> xPos += 1
            Direction.NORTH -> xPos -= 1
            Direction.EAST -> yPos += 1
            Direction.WEST -> yPos -= 1
        }
    }

    fun getSymbol(): Char {
        return when (direction) {
            Direction.SOUTH -> 'v'
            Direction.NORTH -> '^'
            Direction.EAST -> '>'
            Direction.WEST -> '<'
        }
    }

    fun getNewDirection(mapTile: Char): Direction {
        return when (mapTile) {
            '/' -> {
                when (direction) {
                    Direction.SOUTH -> Direction.WEST
                    Direction.NORTH -> Direction.EAST
                    Direction.EAST -> Direction.NORTH
                    Direction.WEST -> Direction.SOUTH
                }
            }
            '\\' -> {
                when (direction) {
                    Direction.SOUTH -> Direction.EAST
                    Direction.NORTH -> Direction.WEST
                    Direction.EAST -> Direction.SOUTH
                    Direction.WEST -> Direction.NORTH
                }
            }
            '+' -> {
                handleCrossing()
            }
            ' ' -> {
                throw RuntimeException("Cart on impossible location")
            }
            else -> direction
        }
    }

    private fun handleCrossing() : Direction {
        val newDirection = when (crossingCount) {
            0 -> crossFirst()
            1 -> direction
            2 -> crossThird()
            else -> throw RuntimeException("Turncount exhausted")
        }

        if(crossingCount == 2) {
            crossingCount = 0
        } else {
            crossingCount += 1
        }

        return newDirection
    }

    private fun crossFirst(): Direction {
        return when (direction) {
            Direction.SOUTH -> Direction.EAST
            Direction.NORTH -> Direction.WEST
            Direction.WEST -> Direction.SOUTH
            Direction.EAST -> Direction.NORTH
        }
    }

    private fun crossThird(): Direction {
        return when (direction) {
            Direction.SOUTH -> Direction.WEST
            Direction.NORTH -> Direction.EAST
            Direction.WEST -> Direction.NORTH
            Direction.EAST -> Direction.SOUTH
        }
    }
}