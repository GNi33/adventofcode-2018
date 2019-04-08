package app.factory.services

import app.factory.model.FabricClaim

interface IFabricCalculator {

    fun parseClaims(claimData: List<String>): List<FabricClaim>

    fun calculateOverlap(claimList: List<FabricClaim>): Int

    fun getNonOverlappingClaims(claimList: List<FabricClaim>): List<Int>

    fun visualizeClaims(claimList: List<FabricClaim>): String
}
