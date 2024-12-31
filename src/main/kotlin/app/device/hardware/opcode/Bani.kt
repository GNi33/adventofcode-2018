package app.device.hardware.opcode

class Bani: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[3]] = registers[values[1]] and values[2]
    }
}
