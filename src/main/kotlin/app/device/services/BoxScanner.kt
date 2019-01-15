package app.device.services

class BoxScanner : IBoxScanner {

    override fun getChecksumOfList(boxIds: List<String>): Int {
        var twoCount = 0
        var threeCount = 0

        boxIds.forEach { id ->
            val pair = getChecksumPairOfBox(id)

            twoCount += pair.first
            threeCount += pair.second
        }

        return twoCount * threeCount
    }

    override fun getChecksumPairOfBox(boxId: String): Pair<Int, Int> {
        var twoCount = 0
        var threeCount = 0

        val countSet = boxId.toList().groupingBy { it }.eachCount()
        val values = countSet.values

        if (values.contains(2)) {
            twoCount = 1
        }

        val maxVal = values.max()

        if (maxVal != null && maxVal > 2) {
            threeCount = 1
        }

        return Pair(twoCount, threeCount)
    }

}