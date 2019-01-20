package app.factory.services

import app.factory.model.Fabric
import app.factory.model.FabricClaim

class FabricCalculator : IFabricCalculator {

    private val fabric = Fabric()

    override fun parseClaims(claimData: List<String>): List<FabricClaim> {
        return claimData.map {
            parseSingleClaim(it)
        }
    }

    override fun calculateOverlap(claimList: List<FabricClaim>): Int {

        println("Calculating Overlap")

        fabric.reset()
        for (claim in claimList) {
            fabric.addClaim(claim)
        }

        return fabric.getOverlappingSectionCount()
    }

    override fun getNonOverlappingClaims(claimList: List<FabricClaim>): List<Int> {

        // todo refactor
        // need to reset now before adding again, because if I call "calculateOverlap" before, the matrix is already set
        // need to refactor anyways, but a way to see if values have been already added would be better
        // it's okay for now, but I want to go over this and make it better anyways later on
        fabric.reset()
        for (claim in claimList) {
            fabric.addClaim(claim)
        }

        return fabric.getNonOverlappingClaims(claimList)
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
