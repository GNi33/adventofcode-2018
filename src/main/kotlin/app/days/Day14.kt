package app.days

import app.elves.recipes.RecipeScoreCalculator
import app.util.InputReader
import mu.KotlinLogging

class Day14 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()
    private val input = inputReader.getDataForDay(DayConsts.DAY_14)
    private val recipeScoreCalculator = RecipeScoreCalculator()

    override fun run() {
        logger.info { "Day 14" }
        logger.info { "Part 01" }

        input.forEach {
            logger.info { recipeScoreCalculator.calculateRecipeScore(it.toInt()) }
        }

        logger.info { "Part 02" }


    }
}
