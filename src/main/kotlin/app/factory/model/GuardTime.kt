package app.factory.model

import java.util.Date
import java.util.GregorianCalendar

class GuardTime {
    lateinit var startTime: Date
    lateinit var endTime: Date

    fun getMinutes(): Int {
        val diff = endTime.time - startTime.time

        return (diff / 1000 / 60).toInt()
    }

    fun getListOfMinutes(): List<Int> {

        val startDateCal = GregorianCalendar.getInstance()
        startDateCal.time = startTime
        val endDateCal = GregorianCalendar.getInstance()
        endDateCal.time = endTime

        return (startDateCal.get(GregorianCalendar.MINUTE) until endDateCal.get(GregorianCalendar.MINUTE)).toList()
    }
}
