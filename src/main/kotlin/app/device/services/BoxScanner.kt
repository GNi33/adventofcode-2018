package app.device.services

class BoxScanner : IBoxScanner {

    override fun getChecksumOfList(boxIds: List<String>): Int {
        val twoCount = 0
        val threeCount = 0

        return twoCount * threeCount
    }

    override fun getChecksumPairOfBox(boxId: String): Pair<Int, Int> {
        val twoCount = 0
        val threeCount = 0

        return Pair(twoCount, threeCount)
    }

}