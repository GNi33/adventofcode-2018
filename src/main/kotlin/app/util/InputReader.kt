package app.util

import app.days.DayConsts
import java.io.File

class InputReader(private var mode: IInputReader.MODE = IInputReader.MODE.MAIN) : IInputReader {

    private val dayInputs = hashMapOf(
        DayConsts.DAY_1 to listOf("calibration-frequencies.txt"),
        DayConsts.DAY_2 to listOf("box-ids.txt"),
        DayConsts.DAY_3 to listOf("fabric-slices.txt"),
        DayConsts.DAY_4 to listOf("guard-shifts.txt"),
        DayConsts.DAY_5 to listOf("polymer.txt"),
        DayConsts.DAY_6 to listOf("coordinates.txt"),
        DayConsts.DAY_7 to listOf("sleigh-assembly-steps.txt"),
        DayConsts.DAY_8 to listOf("navigation-license-file.txt"),
        DayConsts.DAY_9 to listOf("marble-game.txt"),
        DayConsts.DAY_10 to listOf("light-positions.txt"),
        DayConsts.DAY_11 to listOf("fuel-grid-serial-number.txt"),
        DayConsts.DAY_12 to listOf("pots.txt"),
        DayConsts.DAY_13 to listOf("cart-tracks.txt", "cart-tracks-2.txt")
    )

    override fun getDataFromFile(fileName: String): List<String> =
        File(getFullFilePath(fileName)).useLines { it.toList() }

    override fun getDataForDay(dayNum: Int): List<String> = getDataFromFile(dayInputs[dayNum]!![0])

    override fun getDataForDay(dayNum: Int, index: Int): List<String> = getDataFromFile(dayInputs[dayNum]!![index-1])

    override fun setReaderMode(modeToSet: IInputReader.MODE) {
        mode = modeToSet
    }

    private fun getFullFilePath(fileName: String): String = when (mode) {
        IInputReader.MODE.MAIN -> "src/main/resources/$fileName"
        IInputReader.MODE.TEST -> "src/test/resources/$fileName"
    }
}
