package app.days

import app.device.skywatch.LightsInTheSky
import app.util.InputReader

class Day10 : IDay {

    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_10)
    private val lightsInTheSky = LightsInTheSky(input)

    override fun run() {
        println("Day 10")
        println("Part 01 - Get Message from shifting lights")

        lightsInTheSky.simulateSky()
    }
}
