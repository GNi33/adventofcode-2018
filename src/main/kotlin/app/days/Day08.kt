package app.days

import app.device.license.LicenseParser
import app.util.InputReader
import mu.KotlinLogging

class Day08 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_8)
    private val licenseParser = LicenseParser(input.first())

    override fun run() {
        logger.info { "Day 08" }
        logger.info { "Part 01 - Get sum of all metadata of the parsed license tree" }
        logger.info { licenseParser.getMetaDataSum() }

        logger.info { "Part 02 - Get value of root node" }
        logger.info { licenseParser.getValueOfRootNode() }
    }
}
