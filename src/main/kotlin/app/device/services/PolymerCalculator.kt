package app.device.services

class PolymerCalculator : IPolymerCalculator {

    override fun processPolymerReaction(polymer: String): String {
        val match = buildRegex(polymer)
        return polymerReaction(polymer, match)
    }

    override fun getProcessedPolymerLength(polymer: String): Int {
        return processPolymerReaction(polymer).length
    }

    private fun polymerReaction(polymer: String, match: Regex) : String {
        val result = match.replace(polymer, "")

        return when(polymer == result) {
            true -> result
            false -> polymerReaction(result, match)
        }
    }

    private fun buildRegex(polymer: String) : Regex {
        val distinctChars = getDistinctCharacters(polymer)
        val matchList = buildMatchPairList(distinctChars)
        val matchGroupString = "(${matchList.joinToString("|")})"

        return matchGroupString.toRegex()
    }

    private fun buildMatchPairList(charList: List<Char>): List<String> {
        val matchList = mutableListOf<String>()

        for (char in charList) {
            matchList.add("$char${char.toUpperCase()}")
            matchList.add("${char.toUpperCase()}$char")
        }

        return matchList
    }

    private fun getDistinctCharacters(polymer: String) = polymer.toLowerCase().toList().distinct().sorted()
}