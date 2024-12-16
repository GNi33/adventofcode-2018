package app.factory.model

import java.util.*

const val MS_TO_SECONDS = 1000
const val SECONDS_TO_MINUTES = 60

class GuardTime {
    lateinit var startTime: Date
    lateinit var endTime: Date

    fun getMinutes(): Int {
        val diff = endTime.time - startTime.time

        return (diff / MS_TO_SECONDS / SECONDS_TO_MINUTES).toInt()
    }

    fun getListOfMinutes(): List<Int> {

        val startDateCal = GregorianCalendar.getInstance()
        startDateCal.time = startTime
        val endDateCal = GregorianCalendar.getInstance()
        endDateCal.time = endTime

        return (startDateCal.get(GregorianCalendar.MINUTE) until endDateCal.get(GregorianCalendar.MINUTE)).toList()
    }
}
