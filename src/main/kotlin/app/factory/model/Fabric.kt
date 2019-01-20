package app.factory.model

import app.model.Array2D

class Fabric {

    private val matrix = Array2D<Int>()

    fun addClaim(claim: FabricClaim): Fabric {
        for (row in claim.x until claim.x + claim.length) {
            for (col in claim.y until claim.y + claim.height) {

                var value = matrix[row, col]

                if (value == null) {
                    matrix[row, col] = 0
                    value = 0
                }

                matrix[row, col] = value + 1
            }
        }

        return this
    }

    fun getOverlappingSections(): Int {

        var overlapCount = 0

        for (row in 0 until matrix.ySize) {
            for (col in 0 until matrix.xSize) {

                val value = matrix[row, col]

                if (value != null && value > 1) {
                    overlapCount += 1
                }
            }
        }

        return overlapCount
    }
}
