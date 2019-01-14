package app.device.services

import java.io.File

class CalibrationService : ICalibrationService {

    override fun calibrateFrequencies(): Int {

        val calibrationData = getCalibrationData("src/main/resources/calibration-frequencies.txt")

        val calibratedFrequency : Int = calibrationData.fold(0) { acc, it ->
            val operation = it.substring(0,1)
            val value = it.substring(1).toInt()

            when(operation) {
                "+" -> acc + value
                "-" -> acc - value
                else -> {
                    print("ERROR PARSING DATA")
                    acc + 0
                }
            }
        }

        return calibratedFrequency
    }

    private fun getCalibrationData(fileName : String) : List<String> = File(fileName).useLines { it.toList() }

}