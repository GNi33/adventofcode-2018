package app.days

import app.games.marbles.MarbleGame
import mu.KotlinLogging

private const val NUM_OF_PLAYERS = 473
private const val NUM_OF_MARBLES = 70_904

class Day09 : IDay {

    private val logger = KotlinLogging.logger {}

    override fun run() {
        logger.info { "Day 09" }
        logger.info { "Part 01 - Play marble game with 473 players and 70904 marbles" }
        val marbleGamePart01 = MarbleGame(numOfPlayers = NUM_OF_PLAYERS, numOfMarbles = NUM_OF_MARBLES)
        marbleGamePart01.playGame()

        logger.info { marbleGamePart01.getWinningScore() }

        logger.info { "Part 02 - Play marble game with 473 players and 100 times more marbles" }
        val marbleGamePart02 = MarbleGame(numOfPlayers = NUM_OF_PLAYERS, numOfMarbles = NUM_OF_MARBLES * 100)
        marbleGamePart02.playGame()

        logger.info { marbleGamePart02.getWinningScore() }
    }
}
