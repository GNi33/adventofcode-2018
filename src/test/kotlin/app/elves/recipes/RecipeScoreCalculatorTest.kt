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

        val expectedResults = listOf("5158916779", "0124515891", "9251071085", "5941429882")

        for (idx in input.indices) {

            val score = recipeScoreCalculator.calculateRecipeScore(input[idx].toInt())
            Assertions.assertEquals(expectedResults[idx], score)
        }
    }

    @Test
    fun recipeUntilTest() {

        val testInputs = listOf("51589", "01245", "92510", "59414")

        for (idx in input.indices) {
            val score = recipeScoreCalculator.calculateRecipeNumUntil(testInputs[idx])
            Assertions.assertEquals(input[idx].toInt(), score)
        }
    }

}
