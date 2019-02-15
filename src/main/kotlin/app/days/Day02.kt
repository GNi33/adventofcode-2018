package app.days

import app.device.WristDevice

class Day02 : IDay {

    private val wristDevice = WristDevice()

    override fun run() {
        println("Day 02")
        println("Part 01 - Get box Id")
        println(wristDevice.scanBoxes())
        println("Part 02 - Print common letters on the boxes")
        println(wristDevice.retrieveCommonLettersOfFabricBoxes())
    }
}