package app.days

import app.device.fuelgrid.FuelDisplay
import app.util.InputReader
import mu.KotlinLogging

private const val SQUARE_SIZE = 3

class Day11 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_11)

    override fun run() {
        logger.info { "Day 11" }
        logger.info { "Part 01 - Get square with largest total power" }

        val fuelDisplay = FuelDisplay(input.first().toInt())
        val largestCell = fuelDisplay.getLargestTotal(SQUARE_SIZE)

        logger.info { "${largestCell.x}, ${largestCell.y}" }

        logger.info { "Part 02 - Get square with largest total power over all sizes" }

        val largestCellOverSizes = fuelDisplay.getLargestTotalOverSizes()

        logger.info { "${largestCellOverSizes.x}, ${largestCellOverSizes.y}" }
    }
}
