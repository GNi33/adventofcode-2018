package app.factory.services

import app.factory.model.IGuard
import app.factory.util.GuardParser
import java.util.*

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

            println(it.getMinutesAsleep())

            it.getMinutesAsleep()
        } ?: throw Exception()
    }

    override fun getMinuteMostAsleep(guard: IGuard): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
