package app.days

import app.device.skywatch.LightsInTheSky
import app.util.InputReader
import mu.KotlinLogging

class Day10 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_10)
    private val lightsInTheSky = LightsInTheSky(input)

    override fun run() {
        logger.info { "Day 10" }
        logger.info { "Part 01 - Get Message from shifting lights" }

        lightsInTheSky.simulateSky()
    }
}
