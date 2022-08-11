package app.device.plants

import kotlin.Exception

private const val POT_NUM_PADDING = 5
private const val GEN_PADDING = 6

class PlantPotSimulator(input: List<String>, options: Map<String, Boolean> = mapOf()) {
    private val inputParser = PlantInputParser(input)
    private val rulePipe = RulePipe(inputParser.rules)
    private val options: Map<String, Boolean>

    val generations: MutableList<MutableList<Char>> = mutableListOf()
    val potNumbers: MutableList<Int> = mutableListOf()
    val potNumDifferences: MutableList<Int> = mutableListOf()

    init {

        val defaultOptions = mutableMapOf(
            "printGenerations" to false,
            "printPotNumbers" to false
        )

        defaultOptions.putAll(options)

        this.options = defaultOptions
    }

    fun simulateGenerations(genCount: Long = 10): MutableList<MutableList<Char>> {

        val initialState = inputParser.initialState

        generations.add(initialState)
        potNumbers.add(countPlantPotNumsOfGen(initialState))
        potNumDifferences.add(0)

        if (options["printGenerations"] == true) {
            printGeneration(0, generations[0])
        }

        if (options["printPotNumbers"] == true) {
            println(countPlantPotNumsOfGen(initialState))
        }

        var newGen = initialState

        for (i in 0 until genCount) {

            newGen = simulateGeneration(newGen)
            generations.add(newGen)
            potNumbers.add(countPlantPotNumsOfGen(newGen))
            potNumDifferences.add(potNumbers[i.toInt() + 1] - potNumbers[i.toInt()])

            if (options["printGenerations"] == true) {
                printGeneration(i.toInt() + 1, newGen)
            }

            if (options["printPotNumbers"] == true) {
                println((i+1).toString().padStart(POT_NUM_PADDING).plus(": ")
                        .plus(potNumbers[i.toInt() + 1].toString())
                        .plus(" - ")
                        .plus(potNumDifferences[i.toInt() + 1].toString())
                        )
            }
        }

        return generations
    }

    fun printGenerations(generations: MutableList<MutableList<Char>>) {
        val lastGen = generations[generations.lastIndex]
        val lastGenSize = lastGen.size

        val printableGenerations = mutableListOf<MutableList<Char>>()

        generations.forEach {
            printableGenerations.add(padGeneration(it, lastGenSize))
        }

        printableGenerations.forEachIndexed { index, gen ->
            printGeneration(index, gen)
        }
    }

    fun printGeneration(index: Int, generation: MutableList<Char>) {
        println(
            index.toString().padStart(GEN_PADDING).plus(": ")
                 .plus(generation.joinToString("")
                 .plus(" : ").plus(countPlantPotNumsOfGen(generation))))
    }

    fun getIndexOfFirstDiffOccurence(diff: Int) : Int {

        potNumDifferences.forEachIndexed { idx, value ->
            if (value == diff && potNumDifferences[idx+1] == diff && potNumDifferences[idx+2] == diff) {
                return idx
            }
        }

        throw Exception("Not found")

    }

    fun getMostOccurringDifference(): Int {
        val potNumDifferences = getPotNumDifferenceCount()

        val highestEntry = potNumDifferences.maxByOrNull { it.value }

        return highestEntry?.key ?: throw Exception("No diff found")
    }

    private fun getPotNumDifferenceCount(): MutableMap<Int, Int> {
        val potNumDifferenceCt = mutableMapOf<Int, Int>()

        potNumDifferences.forEach {
            potNumDifferenceCt[it] = potNumDifferenceCt.getOrPut(it) { 0 } + 1
        }

        return potNumDifferenceCt
    }

    fun countPlantPotNumbersOfLastGen(generations: MutableList<MutableList<Char>>): Int {
        val lastGen = generations[generations.lastIndex]

        return countPlantPotNumsOfGen(lastGen)
    }

    private fun countPlantPotNumsOfGen(generation: MutableList<Char>): Int {

        var potCount = 0

        generation.forEachIndexed { idx, char ->
            if (char == '#') {
                potCount += (idx - 3)
            }
        }

        return potCount
    }

    private fun padGeneration(generation: MutableList<Char>, size: Int): MutableList<Char> {
        val genSize = generation.size

        for (i in genSize until size) {
            generation.add(i, '.')
        }

        return generation
    }

    private fun simulateGeneration(plantInput: MutableList<Char>): MutableList<Char> {

        return rulePipe.transform(plantInput)
    }
}
