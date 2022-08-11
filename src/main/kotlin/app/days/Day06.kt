package app.days

import app.device.WristDevice
import mu.KotlinLogging

private const val DISTANCE_LIMIT = 10_000;

class Day06 : IDay {

    private val logger = KotlinLogging.logger {}
    private val wristDevice = WristDevice()

    override fun run() {

        logger.info { "Day 06" }
        logger.info { "Part 01 - Get Largest Area of Destination" }
        logger.info { wristDevice.getLargestAreaAroundDestination() }

        logger.info { "Part 02 - Get Area Size closest to all destinations (limit 10000)" }
        logger.info { wristDevice.getAreaSizeClosestToDestinations(DISTANCE_LIMIT) }
    }
}
