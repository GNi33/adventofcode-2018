package app.device.hardware.opcode

class Gtri: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[3]] = if (registers[values[1]] > values[2]) 1 else 0
    }
}
