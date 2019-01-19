package app.device.services

interface IBoxScanner {

    fun getChecksumOfList(boxIds: List<String>): Int

    fun getChecksumPairOfBox(boxId: String): Pair<Int, Int>

    fun getPrototypeFabricBoxes(boxIds: List<String>): List<String>

    fun getCommonLettersOfFabricBoxes(boxIds: List<String>): String
}
