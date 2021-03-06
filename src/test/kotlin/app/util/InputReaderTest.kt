package app.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InputReaderTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)

    @Test
    fun getDataFromFile() {
        val input = inputReader.getDataFromFile("box-ids.txt")

        val expectedList = listOf(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
        )

        assertEquals(expectedList, input)
    }

    @Test
    fun getDataForDay() {
        val input = inputReader.getDataForDay(2)

        val expectedList = listOf(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
        )

        assertEquals(expectedList, input)
    }
}
