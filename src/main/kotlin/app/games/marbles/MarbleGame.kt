package app.games.marbles

import java.util.LinkedList
import kotlin.math.abs

class MarbleGame(private val numOfPlayers: Int, private val numOfMarbles: Int) {

    val marbleCircle: LinkedList<Int> = LinkedList(listOf(0))
    var current: Int = 0
    val players = Array(numOfPlayers) { 0 }


    fun playGame() {
        (1..numOfMarbles).forEach { marbleNum ->

            val currentPlayer = getCurrentPlayer(marbleNum)

            if (marbleNum % 23 == 0) {

                println("Current Index: $current")
                println("Current Size: ${marbleCircle.size}")

                val idxToRemove = getToRemoveMarbleIndex()

                println("Index to remove: $idxToRemove")

                val addToScore = marbleCircle.get(idxToRemove)
                println("Removed marble: $addToScore")

                players[currentPlayer - 1] += (marbleNum + addToScore)
                marbleCircle.removeAt(idxToRemove)

                current = idxToRemove
            } else {
                val newIndex= getIndexOfNewMarble()
                marbleCircle.add(newIndex, marbleNum)

                current = newIndex
            }
        }
    }

    fun getWinner(): Pair<Int, Int> {
        val winningScore = getWinningScore()
        val winningPlayer  = players.indexOf(winningScore) + 1

        return Pair(winningPlayer, winningScore)
    }

    fun getWinningScore() : Int = players.max() ?: throw Exception()

    private fun getCurrentPlayer(marbleNum: Int): Int {
        return when ((marbleNum % numOfPlayers)) {
            0 -> numOfPlayers
            else -> (marbleNum % numOfPlayers)
        }
    }

    private fun getIndexOfNewMarble(): Int {

        return when (marbleCircle.size) {
            1 -> 1
            current + 1 -> 1
            current + 2 -> marbleCircle.size
            else -> current + 2
        }
    }

    private fun getToRemoveMarbleIndex(): Int {

        val idx = current - 7

        if (idx < 0) {
            return marbleCircle.size - abs(idx)
        }

        return idx
    }

}