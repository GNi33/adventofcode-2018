package app.factory.services

import app.factory.model.FabricClaim
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FabricCalculatorTest {

    @Test
    fun parseClaims() {
        val fabricCalculator = FabricCalculator()

        val listOfStrings = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2",
            "#31231 @ 502,780: 120x3"
        )

        val listOfClaims = fabricCalculator.parseClaims(listOfStrings)

        assertEquals(4, listOfClaims.size)

        val exampleClaim = FabricClaim(2, 3, 1, 4, 4)
        assertEquals(exampleClaim, listOfClaims[1])

        val exampleClaim2 = FabricClaim(31_231, 502, 780, 120, 3)
        assertEquals(exampleClaim2, listOfClaims[3])
    }

    @Test
    fun calculateOverlap() {
        val fabricCalculator = FabricCalculator()

        val listOfStrings = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2"
        )

        val listOfClaims = fabricCalculator.parseClaims(listOfStrings)

        val overlap = fabricCalculator.calculateOverlap(listOfClaims)

        assertEquals(4, overlap)
    }

    @Test
    fun getNonOverlappingClaims() {
        val fabricCalculator = FabricCalculator()

        val listOfStrings = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2"
        )

        val listOfClaims = fabricCalculator.parseClaims(listOfStrings)

        val overlap = fabricCalculator.getNonOverlappingClaims(listOfClaims)

        assertEquals(1, overlap.size)
    }
}
