package app.elves.recipes

import app.elves.Elf

private const val RECIPE_SCORE_LENGTH = 10

class RecipeScoreCalculator {

    private var scoreList: MutableList<Int> = mutableListOf(3,7)

    fun calculateRecipeScore(stoppingPoint: Int): String {

        scoreList = mutableListOf(3,7)

        val maxLength = stoppingPoint + RECIPE_SCORE_LENGTH + 1
        val elves: List<Elf> = listOf(Elf(0), Elf(1))

        while (scoreList.size <= maxLength) {
            calculateStep(elves)
        }

        val finalScoreList = MutableList<Int>(RECIPE_SCORE_LENGTH){ 0 }

        for (i in 0 until RECIPE_SCORE_LENGTH) {
            val idx = stoppingPoint + i
            finalScoreList[i] = scoreList[idx]
        }

        return finalScoreList.joinToString("")
    }

    private fun calculateStep(elves: List<Elf>): MutableList<Int> {

        val firstElf = elves[0]
        val secondElf = elves[1]

        val value01 = scoreList[firstElf.position]
        val value02 = scoreList[secondElf.position]

        val newScore = value01 + value02

        if (newScore > 9) {
            scoreList.add(1)
            scoreList.add(newScore - 10)
        } else {
            scoreList.add(newScore)
        }

        val steps01 = value01 + 1
        val steps02 = value02 + 1

        var newPos01 = firstElf.position + steps01
        var newPos02 = secondElf.position + steps02

        newPos01 = calculateNewPos(newPos01, scoreList.size)
        newPos02 = calculateNewPos(newPos02, scoreList.size)

        elves[0].position = newPos01
        elves[1].position = newPos02

        return scoreList
    }

    private fun calculateNewPos(newPos: Int, listLength: Int): Int {
        if (newPos >= listLength) {
            return calculateNewPos(newPos - listLength, listLength)
        }

        return newPos
    }

}
