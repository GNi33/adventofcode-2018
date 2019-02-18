package app.days

import app.device.license.LicenseParser
import app.util.InputReader

class Day08 : IDay {

    val inputReader = InputReader()
    val input = inputReader.getDataForDay(8)
    val licenseParser = LicenseParser(input.first())

    override fun run() {
        println("Day 08")
        println("Get sum of all metadata of the parsed license tree")
        println(licenseParser.getMetaDataSum())

    }
}