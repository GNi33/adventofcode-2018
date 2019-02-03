package app.factory.services

import app.factory.model.IGuard

interface IGuardSpy {

    fun sortInput(lines: List<String>): List<String>

    fun parseInput(lines: List<String>): Map<Int, IGuard>

    fun getGuardLongestAsleep(guards: Map<Int, IGuard>): IGuard

    fun getMinuteMostAsleep(guard : IGuard) : Int

    fun getAsleepGuardsHash() : Int
}