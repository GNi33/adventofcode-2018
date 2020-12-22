package app.util

import app.days.DayConsts
import java.io.File

class InputReader(private var mode: IInputReader.MODE = IInputReader.MODE.MAIN) : IInputReader {

    private val dayInputs = hashMapOf(
        DayConsts.DAY_1 to "calibration-frequencies.txt",
        DayConsts.DAY_2 to "box-ids.txt",
        DayConsts.DAY_3 to "fabric-slices.txt",
        DayConsts.DAY_4 to "guard-shifts.txt",
        DayConsts.DAY_5 to "polymer.txt",
        DayConsts.DAY_6 to "coordinates.txt",
        DayConsts.DAY_7 to "sleigh-assembly-steps.txt",
        DayConsts.DAY_8 to "navigation-license-file.txt",
        DayConsts.DAY_9 to "marble-game.txt",
        DayConsts.DAY_10 to "light-positions.txt",
        DayConsts.DAY_11 to "fuel-grid-serial-number.txt",
        DayConsts.DAY_12 to "pots.txt"
    )

    override fun getDataFromFile(fileName: String): List<String> =
        File(getFullFilePath(fileName)).useLines { it.toList() }

    override fun getDataForDay(dayNum: Int): List<String> = getDataFromFile(dayInputs[dayNum]!!)

    override fun setReaderMode(modeToSet: IInputReader.MODE) {
        mode = modeToSet
    }

    private fun getFullFilePath(fileName: String): String = when (mode) {
        IInputReader.MODE.MAIN -> "src/main/resources/$fileName"
        IInputReader.MODE.TEST -> "src/test/resources/$fileName"
    }
}
