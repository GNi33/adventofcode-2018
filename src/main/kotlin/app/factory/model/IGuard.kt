package app.factory.model

import java.util.Date

interface IGuard {

    val id: Int

    fun fallsAsleep(dateTime: Date)

    fun wakesUp(dateTime: Date)

    fun getMinutesAsleep() : Int

    fun getMinuteAsleepMostWithCount() : Pair<Int, Int>

    fun getMinuteAsleepMost() : Int
}