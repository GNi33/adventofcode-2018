package app.device.services

interface IBoxScanner {

    fun getChecksumOfList(boxIds : List<String>) : Int

    fun getChecksumPairOfBox(boxId: String) : Pair<Int, Int>
}