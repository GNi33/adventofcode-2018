package app.elves.battle

import app.days.DayConsts
import app.util.IInputReader
import app.util.InputReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BattleTest {
    private val inputReader = InputReader(IInputReader.MODE.TEST)

    @Test
    fun positionAndReadingOrderTest() {
        val input = inputReader.getDataForDay(DayConsts.DAY_15, 2)

        val (battlefield, actors) = InputParser.parseInput(input)
        val bfController = BattlefieldController(battlefield)

        val output = bfController.fetchMap(actors)
        val outputOrdered = bfController.fetchMap(actors, true)

        val expectedOutput = listOf(
            "#######",
            "#.G.E.#",
            "#E.G.E#",
            "#.G.E.#",
            "#######"
        )

        val expectedOutputOrder = listOf(
            "#######",
            "#.1.2.#",
            "#3.4.5#",
            "#.6.7.#",
            "#######"
        )

        output.forEachIndexed { index, s ->
            Assertions.assertEquals(expectedOutput[index], s)
        }

        outputOrdered.forEachIndexed { index, s ->
            Assertions.assertEquals(expectedOutputOrder[index], s)
        }
    }

    @Test
    fun targetScanTest() {
        val input = inputReader.getDataForDay(DayConsts.DAY_15, 3)
        val (battlefield, actors) = InputParser.parseInput(input)

        val bfController = BattlefieldController(battlefield)

        /*bfController.determineActionOrder()

        bfController.printMap()

        val elves = bfController.actors.filter { it.getSymbol() == 'E' }
        val elf = elves[0]

        bfController.determineInRangeOfEnemy(elf)*/
    }
}

/*
Then, the unit identifies all of the open squares (.) that are in range of each target; these are the squares which are
adjacent (immediately up, down, left, or right) to any target and which aren't already occupied by a wall or another unit.
Alternatively, the unit might already be in range of a target. If the unit is not already in range of a target, and there
are no open squares which are in range of a target, the unit ends its turn.

If the unit is already in range of a target, it does not move, but continues its turn with an attack. Otherwise, since it
is not in range of a target, it moves.

To move, the unit first considers the squares that are in range and determines which of those squares it could reach in
the fewest steps. A step is a single movement to any adjacent (immediately up, down, left, or right) open (.) square.
Units cannot move into walls or other units. The unit does this while considering the current positions of units and
does not do any prediction about where units will be later. If the unit cannot reach (find an open path to) any of the
squares that are in range, it ends its turn. If multiple squares are in range and tied for being reachable in the fewest
steps, the square which is first in reading order is chosen. For example:

Targets:      In range:     Reachable:    Nearest:      Chosen:
#######       #######       #######       #######       #######
#E..G.#       #E.?G?#       #E.@G.#       #E.!G.#       #E.+G.#
#...#.#  -->  #.?.#?#  -->  #.@.#.#  -->  #.!.#.#  -->  #...#.#
#.G.#G#       #?G?#G#       #@G@#G#       #!G.#G#       #.G.#G#
#######       #######       #######       #######       #######
*/