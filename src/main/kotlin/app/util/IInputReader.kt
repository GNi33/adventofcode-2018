package app.util

import java.io.File

interface IInputReader {

    enum class MODE {
        MAIN, TEST
    }

    fun getDayFile(fileName: String): File
    fun getFileForDay(dayNum: Int): File
    fun getDataFromFile(fileName: String): List<String>
    fun getDataForDay(dayNum: Int): List<String>
    fun getDataForDay(dayNum: Int, index: Int): List<String>

    fun setReaderMode(modeToSet: MODE)
}
