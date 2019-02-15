package app

import app.days.*

class DayContainer {

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
        return when (day) {
            1 -> Day01()
            2 -> Day02()
            3 -> Day03()
            4 -> Day04()
            5 -> Day05()
            6 -> Day06()
            7 -> Day07()
            else -> throw Exception("Day $day not found")
        }
    }
}