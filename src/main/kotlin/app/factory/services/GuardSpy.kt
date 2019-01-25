package app.factory.services

import java.text.SimpleDateFormat

class GuardSpy : IGuardSpy{

    override fun sortInput(lines: List<String>): List<String> {

        val match = Regex("(15\\d\\d)[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]) ([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])")

        return lines.sortedWith(object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int {
                if (o1 != null && o2 != null) {
                    val regexResult1 = match.find(o1)
                    val dateString1 = regexResult1!!.value

                    val regexResult2 = match.find(o2)
                    val dateString2 = regexResult2!!.value

                    val date1 = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString1)
                    val date2 = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString2)

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
}