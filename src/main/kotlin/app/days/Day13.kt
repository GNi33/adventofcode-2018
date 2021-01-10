package app.days

import app.cheat.Day13
import app.mine.CartSimulation
import app.util.InputReader

class Day13 : IDay {

    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_13)

    override fun run() {
        println("Day 13")
        println("Part 01")

        val sim = CartSimulation(input)
        sim.runSimulation()
    }
}