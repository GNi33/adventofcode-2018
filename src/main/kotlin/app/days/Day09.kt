package app.days

import app.games.marbles.MarbleGame
import mu.KotlinLogging

class Day09 : IDay {

    private val logger = KotlinLogging.logger {}
    override fun run() {
        logger.info { "Day 09" }
        logger.info { "Part 01 - Play marble game with 473 players and 70904 marbles" }
        val marbleGamePart01 = MarbleGame(numOfPlayers = 473, numOfMarbles = 70904)
        marbleGamePart01.playGame()

        logger.info { marbleGamePart01.getWinningScore() }

        logger.info { "Part 02 - Play marble game with 473 players and 100 times more marbles" }
        val marbleGamePart02 = MarbleGame(numOfPlayers = 473, numOfMarbles = 70904 * 100)
        marbleGamePart02.playGame()

        logger.info { marbleGamePart02.getWinningScore() }
    }
}
