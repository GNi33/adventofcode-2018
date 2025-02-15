package app.device.hardware

import app.model.DeserializableInputInterface

class SampledInstruction: Instruction(), DeserializableInputInterface {

    var before = mutableListOf<Int>()
    var after = mutableListOf<Int>()

    override fun deserializeInput(input: String) {
        val inputLines = input.split("\r\n")

        before = inputLines[0].substringAfter("Before: [").substringBefore("]").split(", ").map { it.toInt() }.toMutableList()
        values = inputLines[1].split(" ").map { it.toInt() }
        after = inputLines[2].substringAfter("After:  [").substringBefore("]").split(", ").map { it.toInt() }.toMutableList()

        before.add(0)
        before.add(0)

        after.add(0)
        after.add(0)
    }
}
