package app.util

interface IInputReader {

    enum class MODE {
        MAIN, TEST
    }

    fun getDataFromFile(fileName: String): List<String>
    fun getDataForDay(dayNum: Int): List<String>
    fun getDataForDay(dayNum: Int, index: Int): List<String>

    fun setReaderMode(modeToSet: MODE)
}
