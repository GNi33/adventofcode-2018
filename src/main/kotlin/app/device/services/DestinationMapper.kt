package app.device.services

import app.device.model.DestinationMap
import app.model.Point
import kotlin.math.ceil

private const val ALPHABET_COUNT = 26
private const val ASCII_INT_TO_CHAR_OFFSET = 97;

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

        val numOfDestinations = coordMap.size
        val times = ceil(numOfDestinations.toFloat() / ALPHABET_COUNT).toInt()

        val upperAlph = CharArray(times) { (it + ASCII_INT_TO_CHAR_OFFSET).toChar() }
        val alphabet = CharArray(ALPHABET_COUNT) { (it + ASCII_INT_TO_CHAR_OFFSET).toChar() }

        val identifiers = upperAlph.map {
            alphabet.map { c ->
                "$it$c"
            }
        }.flatten()

        return coordMap.withIndex().associateBy({ identifiers[it.index].uppercase() }, { it.value })
    }

    fun printMap() {
        println(
            """
            Map Output
                Width: ${map.getWidth()}
                Height: ${map.getHeight()}

            """.trimIndent()
        )

        val xRange = 0 until map.getHeight()

        for (line in xRange) {
            println(map.getLine(line).toList())
        }
    }
}
