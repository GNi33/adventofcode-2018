package app.device.services

import app.device.model.DestinationMap
import app.model.Point
import kotlin.math.ceil

class DestinationMapper(coordinateList: List<String>) : IDestinationMapper {

    private val coordinates: Map<String, Point>
    private val map: DestinationMap

    init {
        coordinates = parseStringInput(coordinateList)
        map = DestinationMap(coordinates)
    }

    fun determineAreas() {
        map.setAreas()
    }

    fun determineCloseAreas(limit: Int) {
        map.setCloseAreas(limit)
    }

    override fun getLargestAreaSize(): Int {

        val finiteAreas = getFiniteAreas()
        val areaCounts = mutableMapOf<String, Int>()

        for (area in finiteAreas) {
            areaCounts[area] = getAreaSize(area)
        }

        return areaCounts.maxByOrNull { it.value }!!.value
    }

    fun getAreaSize(area: String): Int {

        var count = 0

        val lineCount = map.getHeight()
        val colCount = map.getWidth()

        for (line in 0 until lineCount) {
            for (col in 0 until colCount) {
                if (area == map.getValue(line, col) || area == map.getValue(line, col).uppercase()) {
                    count++
                }
            }
        }

        return count
    }

    fun getFiniteAreas(): List<String> {
        val areas = coordinates.keys.toList()
        val infiniteAreas = getInfiniteAreas()

        return areas.filter {
            !infiniteAreas.contains(it)
        }
    }

    fun getInfiniteAreas(): List<String> {

        val edgeValues = map.getEdgeValues()

        return edgeValues.flatMap {
            it.map { s ->
                s.uppercase()
            }
        }.distinct()
    }

    override fun parseStringInput(input: List<String>): Map<String, Point> {
        val coordMap = input.flatMap {
            val coords = it.split(',')
            listOf(Point(coords.first().trim().toInt(), coords.last().trim().toInt()))
        }

        val numOfDestinations = input.size
        val times = ceil(numOfDestinations.toFloat() / 26).toInt()
        val upperAlph = CharArray(times) { (it + 97).toChar() }

        val alphabet = CharArray(26) { (it + 97).toChar() }

        val identifiers = upperAlph.map {
            alphabet.map { c ->
                "$it$c"
            }
        }.flatten()

        return coordMap.withIndex().associateBy({ identifiers[it.index].uppercase() }, { it.value })
    }

    fun printMap() {
        map.print()
    }
}
