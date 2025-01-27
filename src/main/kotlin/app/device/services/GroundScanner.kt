package app.device.services

import app.device.groundscan.UndergroundMap
import app.device.model.groundscan.ClayVein
import app.model.Point

class GroundScanner(val clayVeins: List<ClayVein>) {

    val undergroundMap: UndergroundMap
    var waterTips = mutableListOf<Point>(Point(500, 0))

    init {
        val bounds = determineBoundaries()
        undergroundMap = UndergroundMap(bounds["xMin"]!!, bounds["xMax"]!!, bounds["yMax"]!!)
    }

    fun determineBoundaries(): Map<String, Int> {

        val xMinCoord = clayVeins.minBy { it.x }
        val xMaxCoord = clayVeins.maxBy { it.x + (it.length - 1) }
        val yMin = 0
        val yMinClay = clayVeins.minBy { it.y }
        val yMaxCoord = clayVeins.maxBy { it.y + (it.height - 1) }

        val xMax = xMaxCoord.x + (xMaxCoord.length - 1) + 1
        val yMax = yMaxCoord.y + (yMaxCoord.height - 1)

        return mapOf("xMin" to xMinCoord.x - 1, "xMax" to xMax, "yMin" to yMin, "yMax" to yMax, "yMinClay" to yMinClay.y)
    }

    fun waterFlowStep() {

        waterTips = waterTips.filter { it.y != -1 }.toMutableList()

        for (tipIdx in waterTips.indices) {
            val currentTip = waterTips[tipIdx]
            val nextTip = Point(currentTip.x, currentTip.y + 1)

            if (nextTip.y > undergroundMap.ySize - 1) {
                waterTips[tipIdx] = Point(-1, -1)
                continue
            }

            val nextTipValue = undergroundMap.arrayMap[nextTip.y, nextTip.x - undergroundMap.xMin]

            if (nextTipValue == '.') {
                undergroundMap.arrayMap[nextTip.y, nextTip.x - undergroundMap.xMin] = '|'
                waterTips[tipIdx] = (nextTip)
            } else if (nextTipValue == '#' || nextTipValue == '~') {

                val leftWall = findWall(Point(nextTip.x, nextTip.y - 1), -1)
                val rightWall = findWall(Point(nextTip.x, nextTip.y - 1), 1)

                if (leftWall != null && rightWall != null) {
                    for (i in leftWall.x + 1 until rightWall.x) {
                        undergroundMap.arrayMap[nextTip.y - 1, i - undergroundMap.xMin] = '~'
                    }

                    waterTips[tipIdx] = Point(currentTip.x, currentTip.y - 1)
                } else {

                    if(leftWall != null) {
                        val ledge = findLedge(leftWall, 1)

                        if(ledge == null) {
                            waterTips[tipIdx] = Point(-1, -1)
                            continue
                        }

                        for (i in leftWall.x + 1 .. ledge.x) {
                            undergroundMap.arrayMap[currentTip.y, i - undergroundMap.xMin] = '|'
                        }

                        undergroundMap.arrayMap[ledge.y, ledge.x - undergroundMap.xMin] = '|'

                        waterTips[tipIdx] = Point(ledge.x, ledge.y)
                    } else if (rightWall != null) {
                        val ledge = findLedge(rightWall, -1)

                        if(ledge == null) {
                            waterTips[tipIdx] = Point(-1, -1)
                            continue
                        }

                        for (i in ledge.x until rightWall.x) {
                            undergroundMap.arrayMap[currentTip.y, i - undergroundMap.xMin] = '|'
                        }

                        undergroundMap.arrayMap[ledge.y, ledge.x - undergroundMap.xMin] = '|'

                        waterTips[tipIdx] = Point(ledge.x, ledge.y)

                    } else if (undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] == '.' && undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] == '.') {
                        // split up tips
                        waterTips.add(Point(currentTip.x - 1, currentTip.y))
                        waterTips.add(Point(currentTip.x + 1, currentTip.y))
                        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] = '|'
                        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] = '|'

                        waterTips[tipIdx] = Point(-1, -1)
                    } else if (undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] == '.') {
                        waterTips[tipIdx] = Point(currentTip.x - 1, currentTip.y)
                        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] = '|'
                    } else if (undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] == '.') {
                        waterTips[tipIdx] = Point(currentTip.x + 1, currentTip.y)
                        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] = '|'
                    } else if (undergroundMap.arrayMap[currentTip.y + 1, currentTip.x - undergroundMap.xMin] == '~') {
                        val leftWallUnder = findWall(Point(currentTip.x, currentTip.y + 1), -1)
                        val rightWallUnder = findWall(Point(currentTip.x, currentTip.y + 1), 1)

                        if(leftWallUnder != null && rightWallUnder != null) {
                            for (i in leftWallUnder.x until rightWallUnder.x + 1) {
                                undergroundMap.arrayMap[currentTip.y, i - undergroundMap.xMin] = '|'
                            }

                            waterTips[tipIdx] = Point(-1, -1)
                            waterTips.add(Point(leftWallUnder.x - 1, currentTip.y))
                            waterTips.add(Point(rightWallUnder.x + 1, currentTip.y))

                            undergroundMap.arrayMap[currentTip.y, leftWallUnder.x - undergroundMap.xMin - 1] = '|'
                            undergroundMap.arrayMap[currentTip.y, rightWallUnder.x - undergroundMap.xMin + 1] = '|'
                        } else {
                            waterTips[tipIdx] = Point(-1, -1)
                        }
                    } else {
                        waterTips[tipIdx] = Point(-1, -1)
                    }
                }
            } else {
                waterTips[tipIdx] = Point(-1, -1)
            }
        }
    }

    fun findWall(src: Point, direction: Int): Point? {

        var current = src

        while (undergroundMap.arrayMap[current.y, current.x - undergroundMap.xMin] != '#') {

            if (undergroundMap.arrayMap[current.y + 1, current.x - undergroundMap.xMin] == '.') {
                return null
            }

            if (undergroundMap.arrayMap[current.y + 1, current.x - undergroundMap.xMin] == '|') {
                return null
            }

            current = Point(current.x + direction, current.y)
        }

        return current
    }

    fun findLedge(src: Point, direction: Int): Point? {

        var current = Point(src.x + direction, src.y)

        while (undergroundMap.arrayMap[current.y + 1, current.x - undergroundMap.xMin] != '.') {

            if(current.x + direction < 0 && current.x + direction > undergroundMap.xSize - 1) {
                return null
            }

            current = Point(current.x + direction, current.y)
        }

        if (undergroundMap.arrayMap[current.y, current.x - undergroundMap.xMin] == '#') {
            return null
        }

        return current
    }

    fun hasWaterTips(): Boolean {
        return waterTips.any { it.y != -1 }
    }

    fun waterTileCount(yMin: Int, yMax: Int, eligibleCharacters: Array<Char> = arrayOf('~', '|')): Int {

        var count = 0

        undergroundMap.arrayMap.forEachRow { idx, it ->
            if (idx < yMin || idx > yMax) {
                return@forEachRow
            }

            count += it.count { c -> c in eligibleCharacters }
        }

        return count
    }
}
