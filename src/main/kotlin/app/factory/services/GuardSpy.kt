package app.factory.services

import app.days.DayConsts
import app.factory.model.IGuard
import app.factory.util.GuardParser
import app.util.IInputReader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GuardSpy : KoinComponent, IGuardSpy {

    private val inputReader by inject<IInputReader>()
    private val guardParser = GuardParser()

    override fun sortInput(lines: List<String>): List<String> {

        return lines.sortedWith(object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int {
                if (o1 != null && o2 != null) {
                    val date1 = guardParser.parseDate(o1)
                    val date2 = guardParser.parseDate(o2)

                    return if(date1 > date2) 1 else if (date1 < date2) -1 else 0
                }

                return 0
            }
        })
    }

    override fun parseInput(lines: List<String>): Map<Int, IGuard> {

        for (line in lines) {
            guardParser.parseLine(line)
        }

        return guardParser.guards
    }

    override fun getGuardLongestAsleep(guards: Map<Int, IGuard>): IGuard {
        return guards.values.maxByOrNull {
            it.getMinutesAsleep()
        } ?: throw Exception()
    }

    override fun getGuardAsleepMostFrequent(guards: Map<Int, IGuard>): IGuard {
        return guards.values.maxByOrNull {
            it.getMinuteAsleepMostWithCount().second
        } ?: throw Exception()
    }

    override fun getMinuteMostAsleep(guard: IGuard): Int {
        return guard.getMinuteAsleepMost()
    }

    override fun getAsleepGuardsHash(): Int {

        val guards = getGuards()

        val guardAsleepLongest = getGuardLongestAsleep(guards)
        val minMostAsleep = getMinuteMostAsleep(guardAsleepLongest)

        return guardAsleepLongest.id * minMostAsleep
    }

    override fun getMostFrequentlyAsleepGuardHash(): Int {
        val guards = getGuards()
        val guard = getGuardAsleepMostFrequent(guards)

        return guard.id * guard.getMinuteAsleepMost()
    }

    private fun getGuards(): Map<Int, IGuard> {
        val guardData = inputReader.getDataForDay(DayConsts.DAY_4)
        val sortedGuardData = sortInput(guardData)

        return parseInput(sortedGuardData)
    }
}
