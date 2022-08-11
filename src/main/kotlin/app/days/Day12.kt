package app.days

import app.device.plants.PlantPotSimulator
import app.util.InputReader
import mu.KotlinLogging

class Day12 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_12)

    override fun run() {
        logger.info { "Day 12" }
        logger.info { "Part 01" }

        val plantPotSimulatorPart01 = PlantPotSimulator(input)

        val generations = plantPotSimulatorPart01.simulateGenerations(20)

        plantPotSimulatorPart01.printGenerations(generations)

        val plantCount = plantPotSimulatorPart01.countPlantPotNumbersOfLastGen(generations)

        logger.info { plantCount }

        logger.info { "Part 02" }

        val plantPotSimulatorPart02 = PlantPotSimulator(input, mapOf("printPotNumbers" to false))

        plantPotSimulatorPart02.simulateGenerations(2000)

        val diff = plantPotSimulatorPart02.getMostOccuringDifference()
        val firstIdx = plantPotSimulatorPart02.getIndexOfFirstDiffOccurence(diff)
        val valueOnIdx = plantPotSimulatorPart02.potNumbers[firstIdx]

        val part02GenNum = 50000000000

        val result = ((part02GenNum - firstIdx) * diff) + valueOnIdx

        logger.info { result }

    }
}
