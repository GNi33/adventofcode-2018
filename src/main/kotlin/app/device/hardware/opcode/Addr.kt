package app.device.hardware.opcode

class Addr: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[THIRD_REG_IDX]] = registers[values[FIRST_REG_IDX]] + registers[values[SECOND_REG_IDX]]
    }
}
