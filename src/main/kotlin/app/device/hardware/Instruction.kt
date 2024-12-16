package app.device.hardware

import app.model.DeserializableInputInterface

class Instruction: DeserializableInputInterface {

    var before = listOf<Int>()
    var instructionValues = listOf<Int>()
    var after = listOf<Int>()

    override fun deserializeInput(input: String) {
        val inputLines = input.split("\r\n")

        before = inputLines[0].substringAfter("Before: [").substringBefore("]").split(", ").map { it.toInt() }
        instructionValues = inputLines[1].split(" ").map { it.toInt() }
        after = inputLines[2].substringAfter("After:  [").substringBefore("]").split(", ").map { it.toInt() }
    }
}
