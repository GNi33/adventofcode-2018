package app.device.model

data class LicenseNode(
    val metaData : List<Int>,
    val subNodes : List<LicenseNode>
)
{
    val metaDataSum : Int = metaData.sum() + subNodes.sumBy {
        it.metaDataSum
    }
}