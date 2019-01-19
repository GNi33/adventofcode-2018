package app.device.services

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BoxScannerTest {

    @Test
    fun testChecksumOfList() {
        val boxScanner = BoxScanner()

        val boxIdList = listOf(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
        )

        assertEquals(12, boxScanner.getChecksumOfList(boxIdList))
    }

    @ParameterizedTest(name = "{0} = {1} , {2}")
    @CsvSource(
        "abcdef , 0 , 0",
        "bababc , 1 , 1",
        "abbcde , 1 , 0",
        "abcccd , 0 , 1",
        "aabcdd , 1 , 0",
        "abcdee , 1 , 0",
        "ababab , 0 , 1"
    )
    fun testChecksumPairs(boxId: String, expectedResultTwos: Int, expectedResultThrees: Int) {
        val boxScanner = BoxScanner()
        val expectedPair = Pair(expectedResultTwos, expectedResultThrees)

        assertEquals(expectedPair, boxScanner.getChecksumPairOfBox(boxId))
    }

    @Test
    fun testPrototypeBoxIds() {
        val boxScanner = BoxScanner()

        val boxIdList = listOf(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        )

        val expectedList = listOf(
            "fghij",
            "fguij"
        )

        assertEquals(expectedList, boxScanner.getPrototypeFabricBoxes(boxIdList))
    }

    @Test
    fun testCommonLetters() {
        val boxScanner = BoxScanner()

        val boxIdList = listOf(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        )

        val expectedResult = "fgij"

        assertEquals(expectedResult, boxScanner.getCommonLettersOfFabricBoxes(boxIdList))
    }
}