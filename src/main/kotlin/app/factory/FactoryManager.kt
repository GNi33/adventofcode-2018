package app.factory

import app.factory.services.IFabricCalculator
import app.util.IInputReader
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class FactoryManager : KoinComponent {

    val inputReader by inject<IInputReader>()
    val fabricCalculator by inject<IFabricCalculator>()

    fun calculateFabricOverlap(): Int {
        val fabricData = inputReader.getDataFromFile("fabric-slices.txt")
        val claims = fabricCalculator.parseClaims(fabricData)
        return fabricCalculator.calculateOverlap(claims)
    }

    fun getNonOverlappingClaim(): Int {
        val fabricData = inputReader.getDataFromFile("fabric-slices.txt")
        val claims = fabricCalculator.parseClaims(fabricData)
        val claimList = fabricCalculator.getNonOverlappingClaims(claims)

        return claimList[0]
    }
}
