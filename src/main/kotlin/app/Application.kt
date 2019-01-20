package app

import app.device.WristDevice
import app.device.services.BoxScanner
import app.device.services.CalibrationService
import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
import app.factory.FactoryManager
import app.factory.services.FabricCalculator
import app.factory.services.IFabricCalculator
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    println("Hello Advent Of Code 2018")

    startKoin(listOf(dependenciesModule))

    val app = Application(args)
    app.device()
}

class Application(argv: Array<String>) {

    private val wristDevice = WristDevice()
    private val factoryManager = FactoryManager()

    fun device() {
        println(wristDevice.calibrate())
        println(wristDevice.firstDoubleFrequency())
        println(wristDevice.scanBoxes())
        println(wristDevice.retrieveCommonLettersOfFabricBoxes())
        println(factoryManager.calculateFabricOverlap())
        println(factoryManager.getNonOverlappingClaim())
    }
}

val dependenciesModule = module {
    single { CalibrationService() as ICalibrationService }
    single { BoxScanner() as IBoxScanner }
    single { FabricCalculator() as IFabricCalculator }
}
