package app.days

import app.factory.FactoryManager
import mu.KotlinLogging

class Day03 : IDay {

    private val logger = KotlinLogging.logger {}
    private val factoryManager = FactoryManager()

    override fun run() {
        logger.info { "Day 03" }
        logger.info { "Part 01 - Calculate Fabric Overlap" }
        logger.info { factoryManager.calculateFabricOverlap() }
        logger.info { "Part 02 - Print only claim that is not overlapping" }
        logger.info { factoryManager.getNonOverlappingClaim() }
    }
}
