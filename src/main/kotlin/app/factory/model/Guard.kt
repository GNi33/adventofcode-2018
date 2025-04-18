package app.factory.model

import java.util.*

class Guard(override val id: Int) : IGuard {

    val guardTimes = mutableMapOf<String, GuardTime>()

    private var activeTimeSlot: GuardTime? = null

    override fun fallsAsleep(dateTime: Date) {
        if (activeTimeSlot != null) {
            error("Guard is already asleep")
        }

        val guardTime = GuardTime()
        guardTime.startTime = dateTime

        activeTimeSlot = guardTime
    }

    override fun wakesUp(dateTime: Date) {

        val guardTime = activeTimeSlot ?: error("Guard is not asleep")
        guardTime.endTime = dateTime

        guardTimes[guardTime.startTime.toString()] = guardTime

        activeTimeSlot = null
    }

    override fun getMinutesAsleep(): Int {
        return guardTimes.values.fold(0) {
            acc, guardTime ->
            acc + guardTime.getMinutes()
        }
    }

    override fun getMinuteAsleepMostWithCount(): Pair<Int, Int> {
        val countOfMinutes = getAsleepMinuteCount()

        if (countOfMinutes.isEmpty()) {
            return Pair(0, 0)
        }

        return try {
            val maxMin = countOfMinutes.maxByOrNull {
                it.value
            } ?: throw Exception()

            maxMin.toPair()
        } catch (ex: Exception) {
            Pair(0, 0)
        }
    }

    override fun getMinuteAsleepMost(): Int {
        val maxMin = getMinuteAsleepMostWithCount()
        return maxMin.first
    }

    private fun getAsleepMinuteCount(): Map<Int, Int> {
        val countOfMinutes: MutableMap<Int, Int> = mutableMapOf()

        for (guardTime in guardTimes.values) {
            val minutes = guardTime.getListOfMinutes()

            for (minute in minutes) {
                if (countOfMinutes[minute] == null) {
                    countOfMinutes[minute] = 0
                }

                val minCount = countOfMinutes[minute] ?: throw Exception()

                countOfMinutes[minute] = minCount + 1
            }
        }

        return countOfMinutes
    }
}
