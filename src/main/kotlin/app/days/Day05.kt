package app.days

import app.device.WristDevice

class Day05 : IDay {

    private val wristDevice = WristDevice()

    override fun run() {
        println("Day 05")
        println("Part 01 - Calculate Polymer Reaction and return number of units (characters) of result")
        println(wristDevice.calculatePolymerReaction())
        println("Part 02 - Calculate shortest Polymer")
        println(wristDevice.calculateShortestPolymerReaction())
    }
}
