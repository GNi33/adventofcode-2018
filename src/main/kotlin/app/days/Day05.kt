package app.days

import app.device.WristDevice
import mu.KotlinLogging

class Day05 : IDay {

    private val logger = KotlinLogging.logger {}
    private val wristDevice = WristDevice()

    override fun run() {
        logger.info { "Day 05" }
        logger.info { "Part 01 - Calculate Polymer Reaction and return number of units (characters) of result" }
        logger.info { wristDevice.calculatePolymerReaction() }
        logger.info { "Part 02 - Calculate shortest Polymer" }
        logger.info { wristDevice.calculateShortestPolymerReaction() }
    }
}
