package app.device.hardware

class NamedInstruction: Instruction() {

    var name = ""

    override fun deserializeInput(input: String) {
        val parts = input.split(" ")
        name = parts[0]
        val valueList = parts.subList(1, parts.size).map { it.toInt() }.toMutableList()

        // First value was opcode id before, so it is irrelevant for named instructions
        values = listOf(0) + valueList
    }
}
