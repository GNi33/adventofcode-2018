package app

import app.days.*
import org.reflections.Reflections

class DayContainer {

    private val reflect = Reflections("app.days")

    companion object {
        const val TOTAL_DAYS = 25
    }

    fun runDays(daysToRun: List<Int>) {
        daysToRun.forEach {
            getDay(it).run()
        }
    }

    fun runAllDays() {
        for (day in 1..TOTAL_DAYS) {
            try {
                getDay(day).run()
            } catch (ex: Exception) {
                println(ex.message)
            }
        }
    }

    private fun getDay(day: Int): IDay {

        val dayClass = getDayClass(day)

        dayClass?.let {
            return dayClass.newInstance()
        }

        throw Exception("Day $day not found")
    }

    private fun getDayClass(day: Int): Class<out IDay>? {
        val dayNum = day.toString().padStart(2, '0')

        return getAllDayClasses()?.find {
            it.simpleName == "Day$dayNum"
        }
    }

    private fun getAllDayClasses(): MutableSet<Class<out IDay>>? = reflect.getSubTypesOf(IDay::class.java)
}