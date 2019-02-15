package app.device.services

interface IPolymerCalculator {
    fun processPolymerReaction(polymer: String): String
    fun getProcessedPolymerLength(polymer: String): Int
    fun getShortestPolymerLength(polymer: String): Int
}