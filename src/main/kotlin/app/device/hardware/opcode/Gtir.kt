package app.device.hardware.opcode

class Gtir: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[THIRD_REG_IDX]] = if (values[FIRST_REG_IDX] > registers[values[SECOND_REG_IDX]]) 1 else 0
    }
}
