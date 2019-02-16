package app

import app.device.services.*
import app.factory.services.FabricCalculator
import app.factory.services.IFabricCalculator
import app.sleigh.model.AssemblyStep
import app.sleigh.model.IAssemblyStep
import app.util.IInputReader
import app.util.InputReader
import org.koin.dsl.module.module
import org.koin.log.EmptyLogger
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    println("Hello Advent Of Code 2018")

    startKoin(listOf(dependenciesModule), logger = EmptyLogger())

    val app = Application(args)
    app.run()
}

class Application(private val argv: Array<String>) {

    private val dayContainer = DayContainer()

    fun run() {
        if (argv.isNotEmpty()) {
            val daysToRun = argv.map { it.toInt() }
            dayContainer.runDays(daysToRun)
        } else {
            dayContainer.runAllDays()
        }
    }
}

val dependenciesModule = module {
    single { InputReader() as IInputReader }
    single { CalibrationService() as ICalibrationService }
    single { BoxScanner() as IBoxScanner }
    single { FabricCalculator() as IFabricCalculator }
    single { PolymerCalculator() as IPolymerCalculator }

    factory { (id: String) -> AssemblyStep(id) as IAssemblyStep }
}
