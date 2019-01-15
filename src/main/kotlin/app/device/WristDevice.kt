package app.device

import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.File

class WristDevice : KoinComponent {

    val calibrationService by inject<ICalibrationService>()
    val boxScanner by inject<IBoxScanner>()

    fun calibrate() : Int {
        val calibrationData = getDataFromFile("src/main/resources/calibration-frequencies.txt")
        return calibrationService.calibrateFrequencies(calibrationData)
    }

    fun firstDoubleFrequency() : Int {
        val calibrationData = getDataFromFile("src/main/resources/calibration-frequencies.txt")
        return calibrationService.findFirstDoubleOccurence(calibrationData)
    }

    fun scanBoxes() : Int {

        val boxIds = getDataFromFile("src/main/resources/box-ids.txt")



        return 0
    }

    private fun getDataFromFile(fileName : String) : List<String> = File(fileName).useLines { it.toList() }
}
