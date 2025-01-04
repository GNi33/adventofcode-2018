package app.device.hardware

import app.model.DeserializableInputInterface

open class Instruction: DeserializableInputInterface {

    var values = listOf<Int>()

    override fun deserializeInput(input: String) {
        values = input.split(" ").map { it.toInt() }
    }
}
