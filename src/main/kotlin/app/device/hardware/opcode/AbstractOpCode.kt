package app.device.hardware.opcode

abstract class AbstractOpCode {
    abstract fun execute(values: List<Int>, registers: MutableList<Int>)
}
