package app.days

import app.games.marbles.MarbleGame

class Day09 : IDay {

    override fun run() {
        println("Day 09")
        println("Part 01 - Play marble game with 473 players and 70904 marbles")
        val marbleGamePart01 = MarbleGame(numOfPlayers = 473, numOfMarbles = 70904)
        marbleGamePart01.playGame()

        println(marbleGamePart01.getWinningScore())

        println("Part 02 - Play marble game with 473 players and 100 times more marbles")
        val marbleGamePart02 = MarbleGame(numOfPlayers = 473, numOfMarbles = 70904 * 100)
        marbleGamePart02.playGame()

        println(marbleGamePart02.getWinningScore())
    }
}
