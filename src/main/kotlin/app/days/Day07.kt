package app.days

import app.sleigh.SleighAssembler

class Day07 : IDay {

    private val sleighAssembler = SleighAssembler()

    override fun run() {
        println("Day 07")
        println("Part 01 - Get correct order of Steps")
        println(sleighAssembler.getOrderOfSteps())
    }
}