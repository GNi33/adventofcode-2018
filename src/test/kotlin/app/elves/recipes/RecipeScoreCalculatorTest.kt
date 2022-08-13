package app.elves.recipes

import app.days.DayConsts
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RecipeScoreCalculatorTest {

    private val recipeScoreCalculator = RecipeScoreCalculator()
    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val input = inputReader.getDataForDay(DayConsts.DAY_14)

    @Test
    fun recipeScoreTest() {

        val expectedResults = listOf("5158916779", "0124515891", "9251071085", "5941429882", "6297310862")

        for (idx in input.indices) {
            Assertions.assertEquals(expectedResults[idx], recipeScoreCalculator.calculateRecipeScore(input[idx].toInt()))
        }
    }

}
