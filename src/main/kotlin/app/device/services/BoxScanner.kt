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

    override fun getPrototypeFabricBoxes(boxIds: List<String>): List<String> {
        val listOfPrototypeFabricBoxes = mutableSetOf<String>()

        val idIterator = boxIds.iterator()

        for (boxId in idIterator) {

            val boxCharArr = boxId.toCharArray()

            boxIds.forEach { id ->

                if (!listOfPrototypeFabricBoxes.contains(id)) {
                    val chArr = id.toMutableList()

                    val filtered = chArr.filterIndexed { index, it -> boxCharArr[index] != it }

                    if (filtered.count() == 1) {
                        listOfPrototypeFabricBoxes.add(boxId)
                        listOfPrototypeFabricBoxes.add(id)
                    }
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
                    val filtered = boxChars.filterIndexed { index, it -> idChars[index] == (it) }

                    filtered.forEach { commonLetters.add(it) }
                }
            }

            outerIdx += 1
        }

        return commonLetters.joinToString("")
    }
}