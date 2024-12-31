package app.device.hardware

import app.device.hardware.opcode.OpCodeFactory

class InstructionTerminal {

    val registers = mutableListOf(0, 0, 0, 0)
    private val oppCodeList = listOf(
        "addr",
        "addi",
        "mulr",
        "muli",
        "banr",
        "bani",
        "borr",
        "bori",
        "setr",
        "seti",
        "gtir",
        "gtri",
        "gtrr",
        "eqir",
        "eqri",
        "eqrr"
    )

    fun checkInstructionCodeCount(instructions : List<Instruction>, cutOff: Int = 3): Int {
        return instructions.count { countPossibleOpcodes(it) >= cutOff }
    }

    fun countPossibleOpcodes(instruction: Instruction): Int {
        return tryInstruction(instruction).filter { it.value }.count()
    }

    fun tryInstruction(instruction: Instruction): Map<String, Boolean> {

        val opCodeFactory = OpCodeFactory()
        val opCodeMap = mutableMapOf<String, Boolean>()

        oppCodeList.forEach { oppCode ->
            instruction.before.forEachIndexed { index, value ->
                registers[index] = value
            }

            opCodeFactory.createOppCode(oppCode).execute(instruction.values, registers)
            opCodeMap[oppCode] = registers == instruction.after
        }

        return opCodeMap
    }

}
