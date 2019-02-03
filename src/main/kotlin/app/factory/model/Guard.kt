package app.factory.model

import java.util.*

class Guard(override val id: Int) : IGuard {

    val guardTimes = mutableMapOf<String, GuardTime>()

    private var activeTimeSlot: GuardTime? = null

    override fun fallsAsleep(dateTime: Date) {
        if (activeTimeSlot != null) {
            throw Exception()
        }

        val guardTime = GuardTime()
        guardTime.startTime = dateTime

        activeTimeSlot = guardTime
    }

    override fun wakesUp(dateTime: Date) {

        val guardTime = activeTimeSlot ?: throw Exception()
        guardTime.endTime = dateTime

        guardTimes.put(guardTime.startTime.toString(), guardTime)

        activeTimeSlot = null
    }

    override fun getMinutesAsleep(): Int {
        return guardTimes.values.fold(0) {
            acc, guardTime ->
                acc + guardTime.getMinutes()
        }
    }
}
