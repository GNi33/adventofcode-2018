package app.days

import app.util.InputReader
import mu.KotlinLogging

class Day15: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_15)

    override fun run() {
        logger.info { "Day 15" }
        logger.info { "Part 01" }

        println(input)

        logger.info { "Part 02" }

    }
}
