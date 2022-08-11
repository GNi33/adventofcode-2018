package app.days

import app.device.WristDevice
import mu.KotlinLogging

class Day01 : IDay {

    private val logger = KotlinLogging.logger {}
    private val wristDevice = WristDevice()

    override fun run() {
        logger.info { "Day 01" }
        logger.info { wristDevice.calibrate() }
        logger.info { wristDevice.firstDoubleFrequency() }
    }
}
