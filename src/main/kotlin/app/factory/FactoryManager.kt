package app.factory

import app.days.DayConsts
import app.factory.services.IFabricCalculator
import app.util.IInputReader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FactoryManager : KoinComponent {

    private val inputReader by inject<IInputReader>()
    private val fabricCalculator by inject<IFabricCalculator>()

    fun calculateFabricOverlap(): Int {
        val fabricData = inputReader.getDataForDay(DayConsts.DAY_3)
        val claims = fabricCalculator.parseClaims(fabricData)
        return fabricCalculator.calculateOverlap(claims)
    }

    fun getNonOverlappingClaim(): Int {
        val fabricData = inputReader.getDataForDay(DayConsts.DAY_3)
        val claims = fabricCalculator.parseClaims(fabricData)
        val claimList = fabricCalculator.getNonOverlappingClaims(claims)

        return claimList[0]
    }
}
