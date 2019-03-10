package app.device.model

data class FuelCell(val x: Int, val y: Int, val serialNo: Int) {

    val power by lazy {
        calculatePower()
    }

    private fun calculatePower() : Int {
        val rackId = x + 10
        var powerLevel = rackId * y
        powerLevel += serialNo
        powerLevel *= rackId

        val hundredsDigit = getHundredsDigit(powerLevel)
        return hundredsDigit - 5
    }

    private fun getHundredsDigit(num: Int) : Int {

        // this is a travesty tbh
        val paddedNums = String.format("%03d", num).toCharArray()
        return paddedNums[paddedNums.lastIndex - 2].toString().toInt()
    }
}