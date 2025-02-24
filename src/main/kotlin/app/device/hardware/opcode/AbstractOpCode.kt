package app.device.hardware.opcode

const val FIRST_REG_IDX = 1
const val SECOND_REG_IDX = 2
const val THIRD_REG_IDX = 3

abstract class AbstractOpCode {
    abstract fun execute(values: List<Int>, registers: MutableList<Int>)
}
