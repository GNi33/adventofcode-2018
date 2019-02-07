package app.device

import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
import app.util.IInputReader
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WristDevice : KoinComponent {

    private val inputReader by inject<IInputReader>()
    private val calibrationService by inject<ICalibrationService>()
    private val boxScanner by inject<IBoxScanner>()

    fun calibrate(): Int {
        val calibrationData = inputReader.getDataFromFile("calibration-frequencies.txt")
        return calibrationService.calibrateFrequencies(calibrationData)
    }

    fun firstDoubleFrequency(): Int {
        val calibrationData = inputReader.getDataFromFile("calibration-frequencies.txt")
        return calibrationService.findFirstDoubleOccurrence(calibrationData)
    }

    fun scanBoxes(): Int {
        val boxIds = inputReader.getDataFromFile("box-ids.txt")
        return boxScanner.getChecksumOfList(boxIds)
    }

    fun retrieveCommonLettersOfFabricBoxes(): String {
        val boxIds = inputReader.getDataFromFile("box-ids.txt")
        return boxScanner.getCommonLettersOfFabricBoxes(boxIds)
    }
}
