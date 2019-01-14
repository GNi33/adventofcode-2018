package app.device

import app.device.services.ICalibrationService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.File

class WristDevice : KoinComponent {

    val calibrationService by inject<ICalibrationService>()

    fun calibrate() : Int {
        val calibrationData = getCalibrationDataFromFile("src/main/resources/calibration-frequencies.txt")
        return calibrationService.calibrateFrequencies(calibrationData)
    }

    fun firstDoubleFrequency() : Int {
        val calibrationData = getCalibrationDataFromFile("src/main/resources/calibration-frequencies.txt")
        return calibrationService.findFirstDoubleOccurence(calibrationData)
    }

    private fun getCalibrationDataFromFile(fileName : String) : List<String> = File(fileName).useLines { it.toList() }
}
