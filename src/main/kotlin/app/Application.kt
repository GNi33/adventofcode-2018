package app

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
