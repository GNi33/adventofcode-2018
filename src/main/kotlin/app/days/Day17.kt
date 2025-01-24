package app.days

import app.device.model.groundscan.ClayVein
import app.device.services.GroundScanner
import app.util.InputParser
import app.util.InputReader
import mu.KotlinLogging

class Day17: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()


    override fun run() {
        logger.info { "Day 17 - Reservoir Research" }
        logger.info { "Part 01" }

        val inputRaw = inputReader.getFileForDay(17).readText()

        val inputParser = InputParser()
        val input = inputParser.parseInput(ClayVein::class, inputRaw, "\r\n")

        val groundScanner = GroundScanner(input)
        val map = groundScanner.undergroundMap

        val bounds = groundScanner.determineBoundaries()

        map.fillMap(input)

        var ct = 0
        while (groundScanner.hasWaterTips()) {
            groundScanner.waterFlowStep()

            ct++
        }

        map.printMap(map.ySize)
        println(groundScanner.waterTips)
        println(groundScanner.waterTileCount(bounds["yMinClay"]!!, bounds["yMax"]!!))

        logger.info { "Part 02" }
    }
}
