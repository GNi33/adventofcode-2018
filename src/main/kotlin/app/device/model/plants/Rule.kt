package app.device.model.plants

class Rule(ruleLine: String) {

    val result: Char
    val rule: String

    // .#.## => #

    init {
        val split = ruleLine.split(" => ")

        rule = split[0]
        result = split[1][0]
    }

    fun applies(window: List<Char>): Boolean {
        return window.joinToString("") == rule
    }
}
