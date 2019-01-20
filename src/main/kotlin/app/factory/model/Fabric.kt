package app.factory.model

import app.model.Array2D

class Fabric {

    private val matrix = Array2D<String>()

    fun addClaim(claim: FabricClaim): Fabric {
        for (row in claim.x..claim.length) {
            for (col in claim.y..claim.height) {
                if (matrix[row, col] != null) {
                    matrix[row, col] = "#"
                } else {
                    matrix[row, col] = "1"
                }
            }
        }

        return this
    }

    fun getOverlappingSections(): Int {

        var overlapCount = 0

        for (row in 0 until matrix.ySize) {
            for (col in 0 until matrix.xSize) {
                if (matrix[row, col] == "#") {
                    overlapCount += 1
                }
            }
        }

        return overlapCount
    }
}
