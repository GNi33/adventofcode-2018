package app.device.services

import app.device.groundscan.MAP_CENTER
import app.device.groundscan.UndergroundMap
import app.device.model.groundscan.ClayVein
import app.model.Point

class GroundScanner(val clayVeins: List<ClayVein>) {

    val undergroundMap: UndergroundMap
    var waterTips = mutableListOf<Point>(Point(MAP_CENTER, 0))

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

        return mapOf(
            "xMin" to xMinCoord.x - 1,
            "xMax" to xMax,
            "yMin" to yMin,
            "yMax" to yMax,
            "yMinClay" to yMinClay.y
        )
    }

    fun waterFlowStep() {

        waterTips = waterTips.filter { it.y != -1 }.toMutableList()

        for (tipIdx in waterTips.indices) {
            val currentTip = waterTips[tipIdx]
            val nextTip = Point(currentTip.x, currentTip.y + 1)

            if (isOutOfBound(nextTip)) {
                setInvalidTip(tipIdx)
                continue
            }

            val nextTipValue = getNextTipValue(nextTip)

            when (nextTipValue) {
                '.' -> moveTipDown(tipIdx, nextTip)
                '#', '~' -> handleWallOrWater(tipIdx, currentTip, nextTip)
                else -> setInvalidTip(tipIdx)
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

        undergroundMap.arrayMap.forEachRow { idx, row ->
            if (idx < yMin || idx > yMax) {
                return@forEachRow
            }

            count += row.count { c -> c in eligibleCharacters }
        }

        return count
    }

    private fun isOutOfBound(point: Point): Boolean {
        return point.y > undergroundMap.ySize - 1
    }

    private fun setInvalidTip(tipIdx: Int) {
        waterTips[tipIdx] = Point(-1, -1)
    }

    private fun getNextTipValue(point: Point): Char {
        return undergroundMap.arrayMap[point.y, point.x - undergroundMap.xMin]
    }

    private fun moveTipDown(tipIdx: Int, nextTip: Point) {
        undergroundMap.arrayMap[nextTip.y, nextTip.x - undergroundMap.xMin] = '|'
        waterTips[tipIdx] = nextTip
    }

    private fun handleWallOrWater(tipIdx: Int, currentTip: Point, nextTip: Point) {
        val leftWall = findWall(Point(nextTip.x, nextTip.y - 1), -1)
        val rightWall = findWall(Point(nextTip.x, nextTip.y - 1), 1)

        if (leftWall != null && rightWall != null) {
            fillBetweenWalls(leftWall, rightWall, nextTip.y - 1)
            waterTips[tipIdx] = Point(currentTip.x, currentTip.y - 1)
        } else {
            handleNoWalls(tipIdx, currentTip, leftWall, rightWall)
        }
    }

    private fun fillBetweenWalls(leftWall: Point, rightWall: Point, y: Int) {
        for (i in leftWall.x + 1 until rightWall.x) {
            undergroundMap.arrayMap[y, i - undergroundMap.xMin] = '~'
        }
    }

    private fun handleNoWalls(tipIdx: Int, currentTip: Point, leftWall: Point?, rightWall: Point?) {
        when {
            leftWall != null -> handleLeftWall(tipIdx, currentTip, leftWall)
            rightWall != null -> handleRightWall(tipIdx, currentTip, rightWall)
            canSplitTips(currentTip) -> splitTips(tipIdx, currentTip)
            canMoveLeft(currentTip) -> moveTipLeft(tipIdx, currentTip)
            canMoveRight(currentTip) -> moveTipRight(tipIdx, currentTip)
            canMoveDown(currentTip) -> addSideWallsAndInvalidate(tipIdx, currentTip)
            else -> setInvalidTip(tipIdx)
        }
    }

    private fun handleLeftWall(tipIdx: Int, currentTip: Point, leftWall: Point) {
        val ledge = findLedge(leftWall, 1)
        if (ledge == null) {
            setInvalidTip(tipIdx)
            return
        }
        fillPathAndMoveTip(tipIdx, currentTip, leftWall.x + 1 .. ledge.x, ledge)
    }

    private fun handleRightWall(tipIdx: Int, currentTip: Point, rightWall: Point) {
        val ledge = findLedge(rightWall, -1)
        if (ledge == null) {
            setInvalidTip(tipIdx)
            return
        }
        fillPathAndMoveTip(tipIdx, currentTip, ledge.x until rightWall.x, ledge)
    }

    private fun fillPathAndMoveTip(tipIdx: Int, currentTip: Point, pathRange: IntRange, ledge: Point) {
        for (i in pathRange) {
            undergroundMap.arrayMap[currentTip.y, i - undergroundMap.xMin] = '|'
        }
        undergroundMap.arrayMap[ledge.y, ledge.x - undergroundMap.xMin] = '|'
        waterTips[tipIdx] = Point(ledge.x, ledge.y)
    }

    private fun canSplitTips(currentTip: Point): Boolean {
        return undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] == '.' &&
                undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] == '.'
    }

    private fun splitTips(tipIdx: Int, currentTip: Point) {
        waterTips.add(Point(currentTip.x - 1, currentTip.y))
        waterTips.add(Point(currentTip.x + 1, currentTip.y))
        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] = '|'
        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] = '|'
        setInvalidTip(tipIdx)
    }

    private fun canMoveLeft(currentTip: Point): Boolean {
        return undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] == '.'
    }

    private fun moveTipLeft(tipIdx: Int, currentTip: Point) {
        waterTips[tipIdx] = Point(currentTip.x - 1, currentTip.y)
        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin - 1] = '|'
    }

    private fun canMoveRight(currentTip: Point): Boolean {
        return undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] == '.'
    }

    private fun moveTipRight(tipIdx: Int, currentTip: Point) {
        waterTips[tipIdx] = Point(currentTip.x + 1, currentTip.y)
        undergroundMap.arrayMap[currentTip.y, currentTip.x - undergroundMap.xMin + 1] = '|'
    }

    private fun canMoveDown(currentTip: Point): Boolean {
        return undergroundMap.arrayMap[currentTip.y + 1, currentTip.x - undergroundMap.xMin] == '~'
    }

    private fun addSideWallsAndInvalidate(tipIdx: Int, currentTip: Point) {
        val leftWallUnder = findWall(Point(currentTip.x, currentTip.y + 1), -1)
        val rightWallUnder = findWall(Point(currentTip.x, currentTip.y + 1), 1)

        if (leftWallUnder != null && rightWallUnder != null) {
            for (i in leftWallUnder.x until rightWallUnder.x + 1) {
                undergroundMap.arrayMap[currentTip.y, i - undergroundMap.xMin] = '|'
            }

            setInvalidTip(tipIdx)
            waterTips.add(Point(leftWallUnder.x - 1, currentTip.y))
            waterTips.add(Point(rightWallUnder.x + 1, currentTip.y))
            undergroundMap.arrayMap[currentTip.y, leftWallUnder.x - undergroundMap.xMin - 1] = '|'
            undergroundMap.arrayMap[currentTip.y, rightWallUnder.x - undergroundMap.xMin + 1] = '|'
        } else {
            setInvalidTip(tipIdx)
        }
    }
}
