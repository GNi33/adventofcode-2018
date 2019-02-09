package app.util

import java.io.File

class InputReader(private var mode: IInputReader.MODE = IInputReader.MODE.MAIN) : IInputReader {

    private val dayInputs = hashMapOf(
        1 to "calibration-frequencies.txt",
        2 to "box-ids.txt",
        3 to "fabric-slices.txt",
        4 to "guard-shifts.txt",
        5 to "polymer.txt",
        6 to "coordinates.txt"
    )

    override fun getDataFromFile(fileName: String): List<String> = File(getFullFilePath(fileName)).useLines { it.toList() }

    override fun getDataForDay(dayNum: Int): List<String> = getDataFromFile(dayInputs[dayNum]!!)

    override fun setReaderMode(modeToSet: IInputReader.MODE) {
        mode = modeToSet
    }

    private fun getFullFilePath(fileName: String) : String = when (mode) {
        IInputReader.MODE.MAIN -> "src/main/resources/$fileName"
        IInputReader.MODE.TEST -> "src/test/resources/$fileName"
    }
}