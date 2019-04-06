package app.days

import app.device.skywatch.LightsInTheSky
import app.util.InputReader

class Day10 : IDay {

    val inputReader = InputReader()
    val input = inputReader.getDataForDay(10)
    val lightsInTheSky = LightsInTheSky(input)

    override fun run() {
        println("Day 10")
        println("Part 01 - Get Message from shifting lights")

        lightsInTheSky.simulateSky()
    }
}