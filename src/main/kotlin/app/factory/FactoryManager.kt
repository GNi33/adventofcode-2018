package app.factory

import app.factory.services.IFabricCalculator
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.File

class FactoryManager : KoinComponent {

    val fabricCalculator by inject<IFabricCalculator>()

    fun calculateFabricOverlap(): Int {
        val fabricData = getDataFromFile("src/main/resources/fabric-slices.txt")
        val claims = fabricCalculator.parseClaims(fabricData)
        return fabricCalculator.calculateOverlap(claims)
    }

    fun getNonOverlappingClaim(): Int {
        val fabricData = getDataFromFile("src/main/resources/fabric-slices.txt")
        val claims = fabricCalculator.parseClaims(fabricData)
        val claimList = fabricCalculator.getNonOverlappingClaims(claims)

        return claimList[0]
    }

    private fun getDataFromFile(fileName: String): List<String> = File(fileName).useLines { it.toList() }
}
