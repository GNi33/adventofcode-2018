package app

import app.device.WristDevice
import app.device.services.BoxScanner
import app.device.services.CalibrationService
import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
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

    fun device() {
        println(wristDevice.calibrate())
        println(wristDevice.firstDoubleFrequency())
        println(wristDevice.scanBoxes())
        println(wristDevice.retrieveCommonLettersOfFabricBoxes())
    }

}

val dependenciesModule = module {
    single {CalibrationService() as ICalibrationService}
    single {BoxScanner() as IBoxScanner}
}
