package app.days

import app.factory.services.GuardSpy

class Day04 : IDay {

    private val guardSpy = GuardSpy()

    override fun run() {

        println("Day 04")
        println("Part 01 - Get Guard Hash for Guard Most asleep * Minute most asleep")
        println(guardSpy.getAsleepGuardsHash())
        println("Part 02 - Get Guard Hash for Guard Most Frequently asleep at one given minute * that minute")
        println(guardSpy.getMostFrequentlyAsleepGuardHash())

    }
}