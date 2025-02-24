package app.device.hardware.opcode

class OpCodeFactory {

    private val opCodeMap: Map<String, () -> AbstractOpCode> = mapOf(
        "borr" to { Borr() },
        "banr" to { Banr() },
        "mulr" to { Mulr() },
        "bori" to { Bori() },
        "bani" to { Bani() },
        "muli" to { Muli() },
        "addr" to { Addr() },
        "addi" to { Addi() },
        "eqrr" to { Eqrr() },
        "eqri" to { Eqri() },
        "eqir" to { Eqir() },
        "gtir" to { Gtir() },
        "gtri" to { Gtri() },
        "gtrr" to { Gtrr() },
        "setr" to { Setr() },
        "seti" to { Seti() }
    )

    fun createOpCode(name: String): AbstractOpCode {
        return opCodeMap[name]?.invoke() ?: throw IllegalArgumentException("Unknown opcode: $name")

    }
}
