package app.days

import app.device.WristDevice
import mu.KotlinLogging

class Day02 : IDay {

    private val logger = KotlinLogging.logger {}
    private val wristDevice = WristDevice()

    override fun run() {
        logger.info { "Day 02" }
        logger.info { "Part 01 - Get box Id" }
        logger.info { wristDevice.scanBoxes() }
        logger.info { "Part 02 - Print common letters on the boxes" }
        logger.info { wristDevice.retrieveCommonLettersOfFabricBoxes() }
    }
}
