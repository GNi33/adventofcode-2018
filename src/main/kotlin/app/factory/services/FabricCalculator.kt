package app.factory.services

import app.factory.model.FabricClaim

class FabricCalculator : IFabricCalculator {

    override fun parseClaims(claimData: List<String>): List<FabricClaim> {
        return claimData.map {
            parseSingleClaim(it)
        }
    }

    override fun calculateOverlap(claimList: List<FabricClaim>): Int {
        return 0
    }

    override fun visualizeClaims(claimList: List<FabricClaim>): String {
        return ""
    }

    private fun parseSingleClaim(claimString: String): FabricClaim {
        val destructuredRegex = "^#([0-9]*) @ ([0-9]*),([0-9]*): ([0-9]*)x([0-9]*)$".toRegex()

        return destructuredRegex.matchEntire(claimString)
            ?.destructured
            ?.let { (id, x, y, width, height) ->
                FabricClaim(id.toInt(), x.toInt(), y.toInt(), width.toInt(), height.toInt())
            }
            ?: throw IllegalArgumentException("Bad input '$claimString'")
    }
}
