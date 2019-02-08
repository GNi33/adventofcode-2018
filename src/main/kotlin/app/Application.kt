package app

import app.device.WristDevice
import app.device.services.*
import app.factory.FactoryManager
import app.factory.services.FabricCalculator
import app.factory.services.GuardSpy
import app.factory.services.IFabricCalculator
import app.util.IInputReader
import app.util.InputReader
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    println("Hello Advent Of Code 2018")

    startKoin(listOf(dependenciesModule))

    val app = Application(args)
    app.run()
}

class Application(argv: Array<String>) {

    private val wristDevice = WristDevice()
    private val factoryManager = FactoryManager()
    private val guardSpy = GuardSpy()

    fun run() {
        println("Day 01")
        println(wristDevice.calibrate())
        println(wristDevice.firstDoubleFrequency())

        println("Day 02")
        println("Part 01 - Get box Id")
        println(wristDevice.scanBoxes())
        println("Part 02 - Print common letters on the boxes")
        println(wristDevice.retrieveCommonLettersOfFabricBoxes())

        println("Day 03")
        println("Part 01 - Calculate Fabric Overlap")
        println(factoryManager.calculateFabricOverlap())
        println("Part 02 - Print only claim that is not overlapping")
        println(factoryManager.getNonOverlappingClaim())

        println("Day 04")

        println("Part 01 - Get Guard Hash for Guard Most asleep * Minute most asleep")
        println(guardSpy.getAsleepGuardsHash())
        println("Part 02 - Get Guard Hash for Guard Most Frequently asleep at one given minute * that minute")
        println(guardSpy.getMostFrequentlyAsleepGuardHash())

        println("Day 05")
        println("Part 01 - Calculate Polymer Reaction and return number of units (characters) of result")
        println(wristDevice.calculatePolymerReaction())

    }
}

val dependenciesModule = module {
    single { InputReader() as IInputReader}
    single { CalibrationService() as ICalibrationService }
    single { BoxScanner() as IBoxScanner }
    single { FabricCalculator() as IFabricCalculator }
    single { PolymerCalculator() as IPolymerCalculator}
}
