package app.days

import app.days.DayConsts.DAY_18
import app.factory.services.LumberManager
import app.util.InputReader
import com.varabyte.kotter.foundation.anim.Anim
import com.varabyte.kotter.foundation.firstSuccess
import com.varabyte.kotter.foundation.runUntilSignal
import com.varabyte.kotter.foundation.session
import com.varabyte.kotter.foundation.text.ColorLayer
import com.varabyte.kotter.foundation.text.black
import com.varabyte.kotter.foundation.text.text
import com.varabyte.kotter.foundation.text.textLine
import com.varabyte.kotter.foundation.timer.addTimer
import com.varabyte.kotter.runtime.terminal.TerminalSize
import com.varabyte.kotter.terminal.system.SystemTerminal
import com.varabyte.kotter.terminal.virtual.VirtualTerminal
import mu.KotlinLogging

private const val PART01_MINUTES = 10
private const val PART02_MINUTES = 1_000_000_000
private const val LOGGING_INTERVAL = 100
private const val PART02_CUTOFF = 1000
private const val WINDOW_SIZE = 3
private const val TERMINAL_SIZE_PADDING = 15

class Day18 : IDay {

    private val logger = KotlinLogging.logger {}
    private val inputReader = InputReader()

    override fun run() {
        logger.info { "Day 18 - Settlers of The North Pole" }
        logger.info { "Part 01" }

        val input = inputReader.getDataForDay(DAY_18)
        val ySize = input.size
        val xSize = input[0].length

        val lumberManager = LumberManager(xSize, ySize, WINDOW_SIZE, input.map { it.toList() })

        repeat(PART01_MINUTES) {
            lumberManager.passMinute()
        }

        logger.info { lumberManager.getLumberyards() * lumberManager.getWoodAcres() }

        logger.info { "Part 02" }

        val lumberManager2 = LumberManager(xSize, ySize, WINDOW_SIZE, input.map { it.toList() })

        render(lumberManager2)

        if (lumberManager2.cycle.first > 0) {
            val cycle = lumberManager2.cycle
            val cycleLength = lumberManager2.minute - cycle.second
            val remainingMinutes = PART02_MINUTES - cycle.first
            val remainingMinutesInCycle = remainingMinutes % cycleLength

            for (i in 1..remainingMinutesInCycle) {
                lumberManager2.passMinute()
            }
        }

        logger.info { lumberManager2.getLumberyards() * lumberManager2.getWoodAcres() }
    }

    private fun render(lumberManager: LumberManager) = session(
        terminal = listOf(
            { SystemTerminal() },
            {
                VirtualTerminal.create(
                    terminalSize = TerminalSize(
                        lumberManager.xSize,
                        lumberManager.ySize + TERMINAL_SIZE_PADDING
                    )
                )
            }
        ).firstSuccess(),
        clearTerminal = true) {

        section {
            textLine("Day 18 - Part 02")
            textLine()
        }.run()

        val cycles = hashMapOf<Int, Pair<Int, Int>>()

        section {
            textLine("Minute: ${lumberManager.minute}")
            black(ColorLayer.BG, isBright = true)
            for (y in 0 until lumberManager.ySize) {
                for (x in 0 until lumberManager.xSize) {
                    scopedState {
                        text(lumberManager.lumberCollectionArea.arrayMap[y, x])
                    }
                }
                textLine()
            }
        }.runUntilSignal {
            addTimer(Anim.ONE_FRAME_60FPS, repeat = true) {
                lumberManager.passMinute()
                rerender()

                if (lumberManager.lastRuns.size > 1) {
                    for (i in lumberManager.lastRuns.size - 1 downTo 1) {
                        if (lumberManager.lastRuns[i].array.contentDeepEquals(lumberManager.lastRuns[0].array)) {
                            // Cycle detected
                            val cycle = i

                            if (cycles[cycle] != null) {
                                cycles[cycle] = cycles[cycle]!!.copy(second = cycles[cycle]!!.second + 1)
                            } else {
                                cycles[cycle] = Pair(lumberManager.minute, 1)
                            }
                            break
                        }
                        rerender()
                    }
                }

                if (lumberManager.minute % LOGGING_INTERVAL == 0) {
                    logger.info { cycles }
                }

                if (lumberManager.minute == PART02_CUTOFF) {
                    logger.info { cycles }

                    if (!cycles.isEmpty()) {
                        val maxCycle = cycles.maxBy { it.value.second }

                        logger.info { "Max cycle: $maxCycle" }
                        lumberManager.cycle = maxCycle.key to maxCycle.value.first
                    }
                    signal()
                }
            }
        }
    }

}
