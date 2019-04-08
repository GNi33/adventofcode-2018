package app.days

import app.device.license.LicenseParser
import app.util.InputReader

class Day08 : IDay {

    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_8)
    private val licenseParser = LicenseParser(input.first())

    override fun run() {
        println("Day 08")
        println("Part 01 - Get sum of all metadata of the parsed license tree")
        println(licenseParser.getMetaDataSum())

        println("Part 02 - Get value of root node")
        println(licenseParser.getValueOfRootNode())
    }
}
