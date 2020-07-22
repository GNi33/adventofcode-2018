package app.device

import app.days.DayConsts
import app.device.services.DestinationMapper
import app.device.services.IBoxScanner
import app.device.services.ICalibrationService
import app.device.services.IPolymerCalculator
import app.util.IInputReader
import org.koin.core.KoinComponent
import org.koin.core.inject

class WristDevice : KoinComponent {

    private val inputReader by inject<IInputReader>()
    private val calibrationService by inject<ICalibrationService>()
    private val boxScanner by inject<IBoxScanner>()
    private val polymerCalculator by inject<IPolymerCalculator>()

    fun calibrate(): Int {
        val calibrationData = inputReader.getDataForDay(DayConsts.DAY_1)
        return calibrationService.calibrateFrequencies(calibrationData)
    }

    fun firstDoubleFrequency(): Int {
        val calibrationData = inputReader.getDataForDay(DayConsts.DAY_1)
        return calibrationService.findFirstDoubleOccurrence(calibrationData)
    }

    fun scanBoxes(): Int {
        val boxIds = inputReader.getDataForDay(DayConsts.DAY_2)
        return boxScanner.getChecksumOfList(boxIds)
    }

    fun retrieveCommonLettersOfFabricBoxes(): String {
        val boxIds = inputReader.getDataForDay(DayConsts.DAY_2)
        return boxScanner.getCommonLettersOfFabricBoxes(boxIds)
    }

    fun calculatePolymerReaction(): Int {
        val polymer = inputReader.getDataForDay(DayConsts.DAY_5).first()
        return polymerCalculator.getProcessedPolymerLength(polymer)
    }

    fun calculateShortestPolymerReaction(): Int {
        val polymer = inputReader.getDataForDay(DayConsts.DAY_5).first()
        return polymerCalculator.getShortestPolymerLength(polymer)
    }

    fun getLargestAreaAroundDestination(): Int {
        val destinations = inputReader.getDataForDay(DayConsts.DAY_6)
        val destinationMapper = DestinationMapper(destinations)
        destinationMapper.determineAreas()

        return destinationMapper.getLargestAreaSize()
    }

    fun getAreaSizeClosestToDestinations(): Int {
        val destinations = inputReader.getDataForDay(DayConsts.DAY_6)
        val destinationMapper = DestinationMapper(destinations)
        destinationMapper.determineCloseAreas(10000)

        return destinationMapper.getAreaSize("#")
    }
}
