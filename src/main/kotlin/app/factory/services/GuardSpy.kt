package app.factory.services

import app.factory.model.IGuard
import app.factory.util.GuardParser
import java.io.File

class GuardSpy : IGuardSpy {

    val guardParser = GuardParser()

    override fun sortInput(lines: List<String>): List<String> {

        return lines.sortedWith(object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int {
                if (o1 != null && o2 != null) {
                    val date1 = guardParser.parseDate(o1)
                    val date2 = guardParser.parseDate(o2)

                    if (date1 > date2) {
                        return 1
                    }

                    if (date1 < date2) {
                        return -1
                    }

                    return 0
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
        return guards.values.maxBy {
            it.getMinutesAsleep()
        } ?: throw Exception()
    }

    override fun getMinuteMostAsleep(guard: IGuard): Int {
        return guard.getMinuteAsleepMost()
    }

    override fun getAsleepGuardsHash(): Int {
        val guardData = getDataFromFile("src/main/resources/guard-shifts.txt")
        val sortedGuardData = sortInput(guardData)
        val guards = parseInput(sortedGuardData)

        val guardAsleepLongest = getGuardLongestAsleep(guards)
        val minMostAsleep = getMinuteMostAsleep(guardAsleepLongest)

        println(guardAsleepLongest.getMinutesAsleep())

        println("Get Guard Id Hash")
        println(guardAsleepLongest.id)
        println(minMostAsleep)
        println(guardAsleepLongest.id * minMostAsleep)

        return guardAsleepLongest.id * minMostAsleep
    }

    private fun getDataFromFile(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}