package app.days

import app.util.InputReader
import mu.KotlinLogging

class Day16: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_16)

    override fun run() {
        logger.info { "Day 16" }
        logger.info { "Part 01" }

        println(input)

        logger.info { "Part 02" }

    }
}
