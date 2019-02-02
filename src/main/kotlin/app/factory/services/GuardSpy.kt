package app.factory.services

import java.text.SimpleDateFormat
import java.util.*

class GuardSpy : IGuardSpy{

    override fun sortInput(lines: List<String>): List<String> {

        return lines.sortedWith(object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int {
                if (o1 != null && o2 != null) {
                    val date1 = parseDate(o1)
                    val date2 = parseDate(o2)

                    if (date1 > date2) {
                        return 1
                    }

                    if(date1 < date2){
                        return -1
                    }

                    return 0
                }

                return 0
            }

        })
    }

    private fun parseDate(line: String): Date {
        val match = Regex("(15\\d\\d)[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]) ([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])")

        val regexResult1 = match.find(line)
        val dateString1 = regexResult1!!.value

        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString1)
    }
}