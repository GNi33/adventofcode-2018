package app.days

import app.mine.CartSimulation
import app.util.InputReader
import mu.KotlinLogging

class Day13 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_13)

    override fun run() {
        logger.info { "Day 13" }
        logger.info { "Part 01" }

        val sim = CartSimulation(input)
        sim.runSimulation()

        logger.info { "Part 02" }

        val sim2 = CartSimulation(input)
        sim2.runSimulationUntilLastCart()


    }
}