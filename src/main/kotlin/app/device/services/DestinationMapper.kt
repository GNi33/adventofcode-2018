package app.device.services

import app.device.model.DestinationMap
import app.model.Point

class DestinationMapper(coordinateList: List<String>) : IDestinationMapper {

    private val map : DestinationMap

    init {
        val coordinates = parseStringInput(coordinateList)
        map = DestinationMap(coordinates)
    }

    override fun getLargestAreaSize(): Int {
        map.print()

        return 0
    }

    override fun parseStringInput(input: List<String>): Map<String, Point> {
        val coordMap = input.flatMap {
            val coords = it.split(',')
            listOf(Point(coords.first().trim().toInt(), coords.last().trim().toInt()))
        }

        val alphabet = CharArray(26) { (it + 97).toChar() }

        return coordMap.withIndex().associateBy({ alphabet[it.index].toUpperCase().toString() }, { it.value })
    }
}