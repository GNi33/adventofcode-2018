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
import mu.KotlinLogging

class Application(private val argv: Array<String>) {

    private val logger = KotlinLogging.logger {}
    private val dayContainer = DayContainer()

    fun run() {
        logger.info { "Advent of Code 2018" }

        if (argv.isNotEmpty()) {
            val daysToRun = argv.map { it.toInt() }
            dayContainer.runDays(daysToRun)
        } else {
            dayContainer.runAllDays()
        }
    }
}
