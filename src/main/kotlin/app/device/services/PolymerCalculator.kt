package app.device.services

class PolymerCalculator : IPolymerCalculator {

    override fun processPolymerReaction(polymer: String): String {
        val match = buildRegex(polymer)
        return polymerReaction(polymer, match)
    }

    override fun getProcessedPolymerLength(polymer: String): Int {
        return processPolymerReaction(polymer).length
    }

    override fun getShortestPolymerLength(polymer: String): Int {

        val fullMatch = buildRegex(polymer)
        val matches = buildSingleRegexList(polymer)
        val results = mutableMapOf<String, Int>()

        for (match in matches) {
            val reducedPolymer = match.replace(polymer, "")
            val result = polymerReaction(reducedPolymer, fullMatch)
            results[match.pattern] = result.length
        }

        return results.minBy { it.value }!!.value
    }

    private fun polymerReaction(polymer: String, match: Regex): String {
        val result = match.replace(polymer, "")

        return when (polymer == result) {
            true -> result
            false -> polymerReaction(result, match)
        }
    }

    private fun buildRegex(polymer: String): Regex {
        val distinctChars = getDistinctCharacters(polymer)
        val matchList = buildMatchPairList(distinctChars)

        val matchGroupString = "(${matchList.flatMap {
            it.toList()
        }.joinToString("|")})"

        return matchGroupString.toRegex()
    }

    private fun buildSingleRegexList(polymer: String): List<Regex> {
        val distinctChars = getDistinctCharacters(polymer)

        return distinctChars.flatMap {
            listOf("($it|${it.toUpperCase()})".toRegex())
        }
    }

    private fun buildMatchPairList(charList: List<Char>): List<Pair<String, String>> {
        val matchList = mutableListOf<Pair<String, String>>()

        for (char in charList) {
            matchList.add(Pair("$char${char.toUpperCase()}", "${char.toUpperCase()}$char"))
        }

        return matchList
    }

    private fun getDistinctCharacters(polymer: String) = polymer.toLowerCase().toList().distinct().sorted()
}