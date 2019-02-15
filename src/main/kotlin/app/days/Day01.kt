package app.days

import app.device.WristDevice

class Day01 : IDay {

    private val wristDevice = WristDevice()

    override fun run() {
        println("Day 01")
        println(wristDevice.calibrate())
        println(wristDevice.firstDoubleFrequency())
    }
}