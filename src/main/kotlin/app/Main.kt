package app

import app.device.services.*
import app.factory.services.FabricCalculator
import app.factory.services.IFabricCalculator
import app.sleigh.model.AssemblyStep
import app.sleigh.model.IAssemblyStep
import app.util.IInputReader
import app.util.InputReader
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

fun main(args: Array<String>) {

    startKoin {
        printLogger(Level.ERROR)
        modules(dependenciesModule)
    }

    val app = Application(args)
    app.run()
}

val dependenciesModule = module {
    single<IInputReader> { InputReader() }
    single<ICalibrationService> { CalibrationService() }
    single<IBoxScanner> { BoxScanner() }
    single<IFabricCalculator> { FabricCalculator() }
    single<IPolymerCalculator> { PolymerCalculator() }

    factory<IAssemblyStep> { (id: String) -> AssemblyStep(id) }
}
