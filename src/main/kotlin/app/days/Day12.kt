package app.days

import app.device.plants.PlantPotSimulator
import app.util.InputReader

class Day12 : IDay {

    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_12)
    private val plantPotSimulator = PlantPotSimulator(input)

    override fun run() {
        println("Day 12")
        println("Part 01")

        plantPotSimulator.simulateGenerations(10)
    }
}
