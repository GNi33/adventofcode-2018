package app

import app.days.DayConsts
import app.days.IDay
import mu.KotlinLogging
import org.reflections.Reflections

class DayContainer {

    private val reflect = Reflections("app.days")
    private val logger = KotlinLogging.logger {}

    fun runDays(daysToRun: List<Int>) {
        daysToRun.forEach {
            getDay(it).run()
        }
    }

    fun runAllDays() {
        for (day in 1..DayConsts.TOTAL_DAYS) {
            try {
                getDay(day).run()
            } catch (ex: Exception) {
                logger.error { (ex.message) }
            }
        }
    }

    private fun getDay(day: Int): IDay {

        val dayClass = getDayClass(day)

        dayClass?.let {
            return dayClass.getDeclaredConstructor().newInstance()
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
