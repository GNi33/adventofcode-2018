package app.days

import app.factory.services.GuardSpy
import mu.KotlinLogging

class Day04 : IDay {

    private val logger = KotlinLogging.logger {}
    private val guardSpy = GuardSpy()

    override fun run() {

        logger.info { "Day 04" }
        logger.info { "Part 01 - Get Guard Hash for Guard Most asleep * Minute most asleep" }
        logger.info { guardSpy.getAsleepGuardsHash() }
        logger.info { "Part 02 - Get Guard Hash for Guard Most Frequently asleep at one given minute * that minute" }
        logger.info { guardSpy.getMostFrequentlyAsleepGuardHash() }
    }
}
