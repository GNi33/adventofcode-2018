package app.days

import app.factory.services.LumberManager
import app.util.InputReader
import mu.KotlinLogging

class Day18: IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()

    override fun run() {
        logger.info { "Day 18 - Settlers of The North Pole" }
        logger.info { "Part 01" }


        val input = inputReader.getDataForDay(18)
        val ySize = input.size
        val xSize = input[0].length

        val lumberManager = LumberManager(xSize, ySize, 3, input.map { it.toList() })

        for (i in 1 .. 10) {
            lumberManager.passMinute()
        }

        logger.info { lumberManager.getLumberyards() * lumberManager.getWoodAcres() }

        logger.info { "Part 02" }

        val lumberManager2 = LumberManager(xSize, ySize, 3, input.map { it.toList() })

        for(i in 1 .. 1000000000) {
            lumberManager2.passMinute()
        }

        logger.info { lumberManager.getLumberyards() * lumberManager.getWoodAcres() }
    }
}
