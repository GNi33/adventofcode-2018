package app.factory.model

import java.util.*

interface IGuard {

    val id: Int

    fun fallsAsleep(dateTime: Date)

    fun wakesUp(dateTime: Date)

    fun getMinutesAsleep() : Int
}
