package app.device.hardware.opcode

class Muli: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[3]] = registers[values[1]] * values[2]
    }
}