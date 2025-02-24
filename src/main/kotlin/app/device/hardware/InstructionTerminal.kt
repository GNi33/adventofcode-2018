package app.device.hardware

import app.device.hardware.opcode.OpCodeFactory

class InstructionTerminal {

    // initialize to invalid value to avoid null checks
    var instructionPointer = -1
    var pointerRegistry = -1

    val registers = mutableListOf(0, 0, 0, 0, 0, 0)
    private val opCodeList = listOf(
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

        opCodeList.forEach { opCode ->
            sampledInstruction.before.forEachIndexed { index, value ->
                registers[index] = value
            }

            opCodeFactory.createOpCode(opCode).execute(sampledInstruction.values, registers)
            opCodeMap[opCode] = registers == sampledInstruction.after
        }

        return opCodeMap
    }

    fun resetRegisters() {
        registers.fill(0)
    }

    fun runInstructions(programInstructions: List<Instruction>, opCodeIds: Map<Int, String>) {
        programInstructions.forEach { instruction ->
            val opCode = OpCodeFactory().createOpCode(opCodeIds[instruction.values[0]]!!)
            opCode.execute(instruction.values, registers)
        }
    }

    fun runNamedInstructions(programInstructions: List<NamedInstruction>) {

        while(instructionPointer < programInstructions.size) {
            val instruction = programInstructions[instructionPointer]
            val opCode = OpCodeFactory().createOpCode(instruction.name)

            if(instructionPointer != -1) {
                registers[pointerRegistry] = instructionPointer
            }

            println("ip=${instructionPointer} ${registers}")

            opCode.execute(instruction.values, registers)

            println(
                "${instruction.name} ${instruction.values[1]} ${instruction.values[2]} ${instruction.values[3]}:" +
                " $registers"
            )

            if(instructionPointer != -1) {
                instructionPointer = registers[pointerRegistry] + 1
            }
        }
    }

}
