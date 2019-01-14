package app

import app.device.WristDevice
import app.device.services.CalibrationService
import app.device.services.ICalibrationService
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    println("Hello Advent Of Code 2018")

    startKoin(listOf(dependenciesModule))

    Application(args).device()
}

class Application(argv: Array<String>) {

    fun device() {
        print(WristDevice().calibrate())
    }

}

val dependenciesModule = module {
    single {CalibrationService() as ICalibrationService}
}
