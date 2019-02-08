package app.device

import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
import app.device.services.IPolymerCalculator
import app.util.IInputReader
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WristDevice : KoinComponent {

    private val inputReader by inject<IInputReader>()
    private val calibrationService by inject<ICalibrationService>()
    private val boxScanner by inject<IBoxScanner>()
    private val polymerCalculator by inject<IPolymerCalculator>()

    fun calibrate(): Int {
        val calibrationData = inputReader.getDataForDay(1)
        return calibrationService.calibrateFrequencies(calibrationData)
    }

    fun firstDoubleFrequency(): Int {
        val calibrationData = inputReader.getDataForDay(1)
        return calibrationService.findFirstDoubleOccurrence(calibrationData)
    }

    fun scanBoxes(): Int {
        val boxIds = inputReader.getDataForDay(2)
        return boxScanner.getChecksumOfList(boxIds)
    }

    fun retrieveCommonLettersOfFabricBoxes(): String {
        val boxIds = inputReader.getDataForDay(2)
        return boxScanner.getCommonLettersOfFabricBoxes(boxIds)
    }

    fun calculatePolymerReaction() : Int {
        val polymer = inputReader.getDataForDay(5).first()
        return polymerCalculator.getProcessedPolymerLength(polymer)
    }

    fun calculateShortestPolymerReaction(): Int {
        val polymer = inputReader.getDataForDay(5).first()
        return polymerCalculator.getShortestPolymerLength(polymer)
    }
}
