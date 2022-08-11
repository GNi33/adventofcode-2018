package app.device.plants

import app.device.model.plants.Rule
import java.util.*

private const val INITIAL_STATE_STR_LENGTH = 15

class PlantInputParser(input: List<String>) {

    val initialState: MutableList<Char>
    val rules: LinkedList<Rule>

    init {

        val initStateLine = input.first()
        val ruleLines = input.drop(2)

        initialState = parseInitialState(initStateLine)
        rules = parseRules(ruleLines)
    }

    private fun extractInitialStateAsString(line: String): String {
        if (line.take(INITIAL_STATE_STR_LENGTH) != "initial state: ") {
            throw Exception("Input malformed")
        }

        return line.removePrefix("initial state: ")
    }

    private fun parseInitialState(line: String): MutableList<Char> {

        val initialStateString = extractInitialStateAsString(line)

        val fillerPlants = "..."
        val stateString = "$fillerPlants$initialStateString$fillerPlants"

        return stateString.map { it }.toMutableList()
    }

    private fun parseRules(ruleLines: List<String>): LinkedList<Rule> {
        val list = LinkedList<Rule>()

        ruleLines.forEach {
            list.add(Rule(it))
        }

        return list
    }
}
