package app.device.hardware.opcode

class Seti: AbstractOpCode() {
    override fun execute(values: List<Int>, registers: MutableList<Int>) {
        registers[values[THIRD_REG_IDX]] = values[FIRST_REG_IDX]
    }
}
