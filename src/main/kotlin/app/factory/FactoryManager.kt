package app.factory

import app.factory.services.IFabricCalculator
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.File

class FactoryManager : KoinComponent {

    val fabricCalculator by inject<IFabricCalculator>()

    fun calculateFabricOverlap(): Int {
        val fabricData = getDataFromFile("src/main/resources/calibration-frequencies.txt")

        return 0
    }

    private fun getDataFromFile(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}
