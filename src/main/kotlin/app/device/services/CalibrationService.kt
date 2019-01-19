package app.device.services

class CalibrationService : ICalibrationService {

    override fun findFirstDoubleOccurence(calibrationData: List<String>): Int {
        return getMultipleFrequencies(calibrationData)
    }

    override fun calibrateFrequencies(calibrationData: List<String>): Int {
        return calibrationData.fold(0) { acc, it -> calculateFrequency(acc, it) }
    }

    private fun getMultipleFrequencies(calibrationData: List<String>): Int {

        var acc = 0
        val occurrences = mutableMapOf(Pair(0, 1))

        while (!occurrences.containsValue(2)) {
            val dataIterator = calibrationData.iterator()

            for (item in dataIterator) {

                acc = calculateFrequency(acc, item)

                var currentValue = occurrences[acc]

                if (currentValue == null) {
                    currentValue = 0
                }

                currentValue += 1

                occurrences.put(acc, currentValue)

                if (currentValue > 1) {
                    return acc
                }
            }
        }

        return acc
    }

    private fun calculateFrequency(acc: Int, frequencyChange: String): Int {
        val operation = frequencyChange.substring(0, 1)
        val value = frequencyChange.substring(1).toInt()

        return when (operation) {
            "+" -> acc + value
            "-" -> acc - value
            else -> {
                print("ERROR PARSING DATA")
                acc + 0
            }
        }
    }
}