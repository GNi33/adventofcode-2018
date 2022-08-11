package app.games.marbles

import java.util.ArrayDeque
import java.util.Deque
import kotlin.math.abs

private const val MARBLE_MULTIPLE_OF = 23
private const val ROTATE_CLOCKWISE_STEPS = 2
private const val ROTATE_COUNTERCLOCKWISE_STEPS = -7

class MarbleGame(private val numOfPlayers: Int, private val numOfMarbles: Int) {

    private val marbleCircle: Deque<Int> = ArrayDeque(listOf(0))
    private val players = LongArray(numOfPlayers) { 0 }

    fun playGame() {
        (1..numOfMarbles).forEach { marbleNum ->
            if (marbleNum % MARBLE_MULTIPLE_OF == 0) {
                val currentPlayer = getCurrentPlayer(marbleNum)
                rotateCircle(ROTATE_COUNTERCLOCKWISE_STEPS)
                players[currentPlayer - 1] += (marbleNum.toLong() + marbleCircle.pop())
            } else {
                rotateCircle(ROTATE_CLOCKWISE_STEPS)
                marbleCircle.addFirst(marbleNum)
            }
        }
    }

    fun getWinner(): Pair<Int, Long> {
        val winningScore = getWinningScore()
        val winningPlayer = players.indexOf(winningScore) + 1

        return Pair(winningPlayer, winningScore)
    }

    fun getWinningScore(): Long = players.maxOrNull() ?: throw Exception()

    private fun getCurrentPlayer(marbleNum: Int): Int {
        return when ((marbleNum % numOfPlayers)) {
            0 -> numOfPlayers
            else -> (marbleNum % numOfPlayers)
        }
    }

    private fun rotateCircle(steps: Int) {
        if (steps >= 0) {
            repeat((0 until steps).count()) {
                marbleCircle.addLast(marbleCircle.removeFirst())
            }
        } else {
            repeat((0 until abs(steps)).count()) {
                marbleCircle.addFirst(marbleCircle.removeLast())
            }
        }
    }
}
