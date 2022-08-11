package app.device.model.plants

class Rule(ruleLine: String) {

    val result: Char
    private val ruleDefinition: String

    // .#.## => #

    init {
        val split = ruleLine.split(" => ")

        ruleDefinition = split[0]
        result = split[1][0]
    }

    fun applies(window: List<Char>): Boolean {
        return window.joinToString("") == ruleDefinition
    }
}
