package app.device.hardware.opcode

class Borr: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[3]] = registers[values[1]] or registers[values[2]]
    }
}
