package app.factory.util

import app.factory.model.IGuard
import java.text.SimpleDateFormat
import java.util.*

class GuardParser {

    val guards : MutableMap<Int, IGuard> = mutableMapOf()
    lateinit var activeGuard: IGuard

    fun parseLine(line: String) {

        val date = parseDate(line)
        val lineType = getLineType(line)

        when (lineType) {
            LineType.BEGINSHIFT -> {

            }
            LineType.FALLASLEEP -> {

            }
            LineType.WAKEUP -> {

            }
            else -> {

            }
        }
    }

    fun getLineType(line: String) : LineType {

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

        val regexResult1 = match.find(line)
        val dateString1 = regexResult1!!.value

        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString1)
    }

}

enum class LineType {
    BEGINSHIFT, FALLASLEEP, WAKEUP
}
