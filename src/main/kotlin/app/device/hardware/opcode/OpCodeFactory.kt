package app.device.hardware.opcode

class OpCodeFactory {
    fun createOpCode(name: String): AbstractOpCode {
        when (name) {
            "borr" -> return Borr()
            "banr" -> return Banr()
            "mulr" -> return Mulr()
            "bori" -> return Bori()
            "bani" -> return Bani()
            "muli" -> return Muli()
            "addr" -> return Addr()
            "addi" -> return Addi()
            "eqrr" -> return Eqrr()
            "eqri" -> return Eqri()
            "eqir" -> return Eqir()
            "gtir" -> return Gtir()
            "gtri" -> return Gtri()
            "gtrr" -> return Gtrr()
            "setr" -> return Setr()
            "seti" -> return Seti()
            else -> throw IllegalArgumentException("Unknown opcode: $name")
        }
    }
}
