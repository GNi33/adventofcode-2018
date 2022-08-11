package app.days

import app.sleigh.SleighAssembler
import mu.KotlinLogging

private const val NUM_WORKERS = 5

class Day07 : IDay {

    private val logger = KotlinLogging.logger {}
    private val sleighAssembler = SleighAssembler()

    override fun run() {
        logger.info { "Day 07" }
        logger.info { "Part 01 - Get correct order of Steps" }
        logger.info { sleighAssembler.getOrderOfSteps() }

        logger.info { "Part 02 - Get time spent on steps with multiple workers" }
        logger.info { sleighAssembler.getTimeSpent(NUM_WORKERS) }
    }
}
