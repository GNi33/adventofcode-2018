package app.factory.services

import app.factory.model.FabricClaim
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FabricCalculatorTest {

    @Test
    fun parseClaims() {
        val fabricCalculator = FabricCalculator()

        val listOfStrings = listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2")

        val listOfClaims = fabricCalculator.parseClaims(listOfStrings)

        assertEquals(3, listOfClaims.size)

        val exampleClaim = FabricClaim(2, 3, 1, 4, 4)

        assertEquals(exampleClaim, listOfClaims[1])
    }

    @Test
    fun calculateOverlap() {
    }

    @Test
    fun visualizeClaims() {
    }
}