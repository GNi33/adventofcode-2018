package app.elves.battle

import app.elves.battle.model.Actor
import app.elves.battle.model.Battlefield
import app.elves.battle.model.Elf
import app.elves.battle.model.Goblin
import app.model.Point

object InputParser {

    fun parseInput(input: List<String>): Pair<Battlefield, MutableList<Actor>> {
        val xSize = input.size
        val ySize = input.maxByOrNull { it.count() }?.count() ?: throw Exception("Parsing went wrong")

        val battlefield = Battlefield(xSize, ySize)
        val actors = mutableListOf<Actor>()

        var ct = 0

        input.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { charIndex, char ->

                var mapChar = char

                when (char) {
                    'G' -> {
                        mapChar = '.'
                        actors.add(
                            Goblin(Point(charIndex, rowIndex), ++ct)
                        )
                    }
                    'E' -> {
                        mapChar = '.'
                        actors.add(
                            Elf(Point(charIndex, rowIndex), ++ct)
                        )
                    }
                }

                battlefield.arrayMap[rowIndex, charIndex] = mapChar
            }
        }

        return Pair(battlefield, actors)
    }

}
