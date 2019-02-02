package app.factory.services

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GuardSpyTest {

    @Test
    fun sortInput() {
        val guardSpy = GuardSpy()

        val expectedLines = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
        )

        val lines = listOf(
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-01 00:30] falls asleep"
        )

        val sortedLines = guardSpy.sortInput(lines)

        assertEquals(expectedLines, sortedLines)
    }

    @Test
    fun parseInput() {
        val guardSpy = GuardSpy()

        val lines = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
        )

        val guards = guardSpy.parseInput(lines)

        assertEquals(2, guards.size)
    }
}