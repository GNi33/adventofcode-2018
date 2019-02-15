package app.days

import app.device.WristDevice

class Day06 : IDay {

    private val wristDevice = WristDevice()

    override fun run() {

        println("Day 06")
        println("Part 01 - Get Largest Area of Destination")
        println(wristDevice.getLargestAreaAroundDestination())

        println("Part 02 - Get Area Size closest to all destinations (limit 10000)")
        println(wristDevice.getAreaSizeClosestToDestinations())

    }
}