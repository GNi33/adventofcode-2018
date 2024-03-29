package app.device.model

data class LicenseNode(
    val metaData: List<Int>,
    val subNodes: List<LicenseNode>
) {
    val metaDataSum: Int = metaData.sum() + subNodes.sumOf {
        it.metaDataSum
    }

    val valueOfNode: Int =
        when (subNodes.isEmpty()) {
            true -> metaDataSum
            false -> metaData.sumOf {
                subNodes.getOrNull(it - 1)?.valueOfNode ?: 0
            }
        }
}
