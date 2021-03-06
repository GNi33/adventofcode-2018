package app.days

import app.device.fuelgrid.FuelDisplay
import app.util.InputReader

class Day11 : IDay {

    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_11)

    override fun run() {
        println("Day 11")
        println("Part 01 - Get square with largest total power")

        val fuelDisplay = FuelDisplay(input.first().toInt())
        val largestCell = fuelDisplay.getLargestTotal()

        println("${largestCell.x}, ${largestCell.y}")

        println("Part 02 - Get square with largest total power over all sizes")

        val largestCellOverSizes = fuelDisplay.getLargestTotalOverSizes()

        println("${largestCellOverSizes.x}, ${largestCellOverSizes.y}")
    }
}
