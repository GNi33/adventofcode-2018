package app.factory.util

import app.factory.model.Guard
import app.factory.model.IGuard
import java.text.SimpleDateFormat
import java.util.Date

class GuardParser {

    val guards: MutableMap<Int, IGuard> = mutableMapOf()
    private lateinit var activeGuard: IGuard

    enum class LineType {
        BEGINSHIFT, FALLASLEEP, WAKEUP
    }

    fun parseLine(line: String) {

        val date = parseDate(line)
        val lineType = getLineType(line)

        when (lineType) {
            LineType.BEGINSHIFT -> {
                val guardId = parseGuardId(line)
                activeGuard = getGuard(guardId)
            }
            LineType.FALLASLEEP -> {
                activeGuard.fallsAsleep(date)
            }
            LineType.WAKEUP -> {
                activeGuard.wakesUp(date)
            }
        }
    }

    private fun parseGuardId(line: String): Int {
        val match = Regex("#(\\d+)")

        val result = match.find(line)

        val id = result?.groupValues?.last() ?: throw Exception()

        return id.toInt()
    }

    private fun getGuard(id: Int): IGuard {
        if (guards[id] == null) {
            guards[id] = Guard(id)
        }

        return guards[id] ?: throw Exception()
    }

    private fun getLineType(line: String): LineType {

        val splitString = line.split(' ')

        val lastWord = splitString.last()

        return when (lastWord) {
            "shift" -> LineType.BEGINSHIFT
            "asleep" -> LineType.FALLASLEEP
            "up" -> LineType.WAKEUP
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    fun parseDate(line: String): Date {
        val match = Regex("(15\\d\\d)[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]) ([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])")

        val regexResult = match.find(line)
        val dateString = regexResult!!.value

        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString)
    }
}
