package app.device.services

interface ICalibrationService {

    fun calibrateFrequencies(calibrationData: List<String>): Int

    fun findFirstDoubleOccurence(calibrationData: List<String>): Int
}
