package app.device.hardware.opcode

class Gtri: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[THIRD_REG_IDX]] = if (registers[values[FIRST_REG_IDX]] > values[SECOND_REG_IDX]) 1 else 0
    }
}
