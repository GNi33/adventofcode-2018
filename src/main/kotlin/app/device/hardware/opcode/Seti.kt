package app.device.hardware.opcode

class Seti: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[3]] = values[1]
    }
}
