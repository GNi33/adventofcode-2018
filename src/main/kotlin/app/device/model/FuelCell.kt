package app.device.model

import java.util.*

private const val ID_PADDING = 10
private const val POWER_LEVEL_SUBSTRACT = 5

data class FuelCell(val x: Int, val y: Int, val serialNo: Int) {

    val power by lazy {
        calculatePower()
    }

    private fun calculatePower(): Int {
        val rackId = x + ID_PADDING
        var powerLevel = rackId * y
        powerLevel += serialNo
        powerLevel *= rackId

        val hundredsDigit = getHundredsDigit(powerLevel)
        return hundredsDigit - POWER_LEVEL_SUBSTRACT
    }

    private fun getHundredsDigit(num: Int): Int {

        // this is a travesty tbh
        val paddedNums = String.format(Locale.ENGLISH, "%03d", num).toCharArray()
        return paddedNums[paddedNums.lastIndex - 2].toString().toInt()
    }
}
