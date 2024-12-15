package app.days

import app.device.plants.PlantPotSimulator
import app.util.InputReader
import mu.KotlinLogging

private const val NUM_OF_GENERATIONS: Long = 20
private const val PART02_NUM_OF_MAX_GENERATIONS: Long = 20

class Day12 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_12)

    override fun run() {
        logger.info { "Day 12" }
        logger.info { "Part 01" }

        val plantPotSimulatorPart01 = PlantPotSimulator(input)

        val generations = plantPotSimulatorPart01.simulateGenerations(NUM_OF_GENERATIONS)

        plantPotSimulatorPart01.printGenerations(generations)

        val plantCount = plantPotSimulatorPart01.countPlantPotNumbersOfLastGen(generations)

        logger.info { plantCount }

        logger.info { "Part 02" }

        val plantPotSimulatorPart02 = PlantPotSimulator(input, mapOf("printPotNumbers" to false))

        plantPotSimulatorPart02.simulateGenerations(NUM_OF_GENERATIONS * 100)

        val diff = plantPotSimulatorPart02.getMostOccurringDifference()
        val firstIdx = plantPotSimulatorPart02.getIndexOfFirstDiffOccurrence(diff)
        val valueOnIdx = plantPotSimulatorPart02.potNumbers[firstIdx]

        val part02GenNum = PART02_NUM_OF_MAX_GENERATIONS

        val result = ((part02GenNum - firstIdx) * diff) + valueOnIdx

        logger.info { result }

    }
}
