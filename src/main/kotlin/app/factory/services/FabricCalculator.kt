package app.factory.services

import app.factory.model.FabricClaim

class FabricCalculator : IFabricCalculator {

    override fun parseClaims(claimData: List<String>): List<FabricClaim> {

        return listOf<FabricClaim>()
    }

    override fun calculateOverlap(claimList: List<FabricClaim>): Int {
        return 0
    }

    override fun visualizeClaims(claimList: List<FabricClaim>): String {
        return ""
    }

    private fun parseSingleClaim(claimString: String): FabricClaim {

        return FabricClaim(1, 1, 1, 1, 1)
    }
}