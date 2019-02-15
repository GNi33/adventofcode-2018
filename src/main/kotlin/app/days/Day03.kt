package app.days

import app.factory.FactoryManager

class Day03 : IDay {

    private val factoryManager = FactoryManager()

    override fun run() {
        println("Day 03")
        println("Part 01 - Calculate Fabric Overlap")
        println(factoryManager.calculateFabricOverlap())
        println("Part 02 - Print only claim that is not overlapping")
        println(factoryManager.getNonOverlappingClaim())
    }
}