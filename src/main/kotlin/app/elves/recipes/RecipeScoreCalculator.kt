package app.elves.recipes

import app.elves.Elf

private const val FIRST_INITIAL_RECIPE_SCORE = 3
private const val SECOND_INITIAL_RECIPE_SCORE = 7
private const val RECIPE_SCORE_LENGTH = 10
private const val MAX_SINGLE_DIGIT = 9
private const val DIGIT_CONVERSION_OFFSET = 10

class RecipeScoreCalculator {

    private var scoreList: MutableList<Int> = mutableListOf(FIRST_INITIAL_RECIPE_SCORE, SECOND_INITIAL_RECIPE_SCORE)

    fun calculateRecipeScore(stoppingPoint: Int): String {

        scoreList = mutableListOf(FIRST_INITIAL_RECIPE_SCORE, SECOND_INITIAL_RECIPE_SCORE)

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

    fun calculateRecipeNumUntil(sequence: String): Int {

        val seqLen = sequence.length
        scoreList = mutableListOf(FIRST_INITIAL_RECIPE_SCORE, SECOND_INITIAL_RECIPE_SCORE)

        val elves: List<Elf> = listOf(Elf(0), Elf(1))
        var score = 0

        while (score == 0) {
            calculateStep(elves)

            if (scoreList.size > seqLen) {
                val lastSeq = scoreList.takeLast(seqLen+1).joinToString("")

                val lastSeq01 = lastSeq.dropLast(1)
                val lastSeq02 = lastSeq.drop(1)

                if (lastSeq01 == sequence || lastSeq02 == sequence) {
                    score = scoreList.size - seqLen

                    if (lastSeq01 == sequence) {
                        score -= 1
                    }
                }
            }
        }

        return score
    }

    private fun calculateStep(elves: List<Elf>): MutableList<Int> {

        val firstElf = elves[0]
        val secondElf = elves[1]

        val value01 = scoreList[firstElf.position]
        val value02 = scoreList[secondElf.position]

        val newScore = value01 + value02

        if (newScore > MAX_SINGLE_DIGIT) {
            scoreList.add(1)
            scoreList.add(newScore - DIGIT_CONVERSION_OFFSET)
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
