package app.games.marbles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MarbleGameTest {

    @Test
    fun marbleGameTestMinimal() {

        val marbleGame = MarbleGame(9, 25)

        marbleGame.playGame()

        assertEquals(32, marbleGame.getWinningScore())
        assertEquals(5, marbleGame.getWinner().first)
    }

    @Test
    fun marbleGameTestTenPlayers() {

        val marbleGame = MarbleGame(10, 1618)

        marbleGame.playGame()

        assertEquals(8317, marbleGame.getWinningScore())
    }

    @Test
    fun marbleGameTestThirteenPlayers() {

        val marbleGame = MarbleGame(13, 7999)

        marbleGame.playGame()

        assertEquals(146_373, marbleGame.getWinningScore())
    }

    @Test
    fun marbleGameTestSeventeenPlayers() {

        val marbleGame = MarbleGame(17, 1104)

        marbleGame.playGame()

        assertEquals(2764, marbleGame.getWinningScore())
    }

    @Test
    fun marbleGameTestTwentyonePlayers() {

        val marbleGame = MarbleGame(21, 6111)

        marbleGame.playGame()

        assertEquals(54_718, marbleGame.getWinningScore())
    }

    @Test
    fun marbleGameTestThirtyPlayers() {

        val marbleGame = MarbleGame(30, 5807)

        marbleGame.playGame()

        assertEquals(37_305, marbleGame.getWinningScore())
    }
}
