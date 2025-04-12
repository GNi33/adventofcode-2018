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

        val maxVal = values.maxOrNull()

        if (maxVal != null && maxVal > 2) {
            threeCount = 1
        }

        return Pair(twoCount, threeCount)
    }

    override fun getPrototypeFabricBoxes(boxIds: List<String>): List<String> {
        val listOfPrototypeFabricBoxes = mutableSetOf<String>()
        val idIterator = boxIds.iterator()

        for (boxId in idIterator) {
            boxIds.forEach { id ->
                if (shouldAddToPrototypeBoxes(boxId, id, listOfPrototypeFabricBoxes)) {
                    listOfPrototypeFabricBoxes.add(boxId)
                    listOfPrototypeFabricBoxes.add(id)
                }
            }
        }

        return listOfPrototypeFabricBoxes.toList()
    }

    override fun getCommonLettersOfFabricBoxes(boxIds: List<String>): String {

        val fabricBoxIds = getPrototypeFabricBoxes(boxIds)

        val commonLetters = mutableListOf<Char>()

        val fabricBoxIterator = fabricBoxIds.iterator()

        // very ugly solution
        var outerIdx = 0
        for (fabricBoxId in fabricBoxIterator) {
            val boxChars = fabricBoxId.toCharArray()

            fabricBoxIds.forEachIndexed { idx, id ->

                if (fabricBoxId != id && idx >= outerIdx) {
                    val idChars = id.toCharArray()
                    val filtered = boxChars.filterIndexed { index, boxChar -> idChars[index] == (boxChar) }

                    filtered.forEach { commonLetters.add(it) }
                }
            }

            outerIdx += 1
        }

        return commonLetters.joinToString("")
    }

    private fun shouldAddToPrototypeBoxes(boxId: String, id: String, prototypeBoxes: Set<String>): Boolean {
        if (prototypeBoxes.contains(id)) return false

        val boxCharArr = boxId.toCharArray()
        val chArr = id.toMutableList()
        val filtered = chArr.filterIndexed { index, chId -> boxCharArr[index] != chId }

        return filtered.count() == 1
    }
}
