package app.device

import app.device.services.ICalibrationService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WristDevice : KoinComponent {

    val calibrationService by inject<ICalibrationService>()

    fun calibrate() = calibrationService.calibrateFrequencies()

}


