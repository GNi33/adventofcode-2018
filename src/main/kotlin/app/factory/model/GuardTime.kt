package app.factory.model

import java.util.*

class GuardTime {
    lateinit var startTime: Date
    lateinit var endTime: Date

    fun getMinutes() : Int {
        val diff = endTime.time - startTime.time

        return (diff / 1000 / 60).toInt()

    }
}