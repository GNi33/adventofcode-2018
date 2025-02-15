package app.device.hardware

import app.device.hardware.opcode.OpCodeFactory

class InstructionTerminal {

    val registers = mutableListOf(0, 0, 0, 0, 0, 0)
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

    fun checkInstructionCodeCount(sampledInstructions : List<SampledInstruction>, cutOff: Int = 3): Int {
        return sampledInstructions.count { countPossibleOpcodes(it) >= cutOff }
    }

    fun countPossibleOpcodes(sampledInstruction: SampledInstruction): Int {
        return tryInstruction(sampledInstruction).filter { it.value }.count()
    }

    fun tryInstruction(sampledInstruction: SampledInstruction): Map<String, Boolean> {

        val opCodeFactory = OpCodeFactory()
        val opCodeMap = mutableMapOf<String, Boolean>()

        oppCodeList.forEach { oppCode ->
            sampledInstruction.before.forEachIndexed { index, value ->
                registers[index] = value
            }

            opCodeFactory.createOpCode(oppCode).execute(sampledInstruction.values, registers)
            opCodeMap[oppCode] = registers == sampledInstruction.after
        }

        return opCodeMap
    }

    fun resetRegisters() {
        registers.clear()
        registers.addAll(listOf(0, 0, 0, 0))
    }

    fun runInstructions(programInstructions: List<Instruction>, opCodeIds: Map<Int, String>) {
        programInstructions.forEach { instruction ->
            val opCode = OpCodeFactory().createOpCode(opCodeIds[instruction.values[0]]!!)
            opCode.execute(instruction.values, registers)
        }
    }

}
