package app.device.services

interface ICalibrationService {

    fun calibrateFrequencies(calibrationData: List<String>): Int

    fun findFirstDoubleOccurrence(calibrationData: List<String>): Int
}
