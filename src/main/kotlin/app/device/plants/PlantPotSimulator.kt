package app.device.plants

class PlantPotSimulator(input: List<String>) {
    private val inputParser = PlantInputParser(input)
    private val rulePipe = RulePipe(inputParser.rules)

    fun simulateGenerations(genCount: Int = 10): MutableList<MutableList<Char>> {
        val initialState = inputParser.initialState
        val generations = mutableListOf<MutableList<Char>>()

        generations.add(initialState)

        var newGen = initialState

        for (i in 0 until genCount) {

            newGen = simulateGeneration(newGen)

            generations.add(newGen)
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

        printableGenerations.forEachIndexed { index, mutableList ->
            println(index.toString().padStart(2).plus(": ").plus(mutableList.joinToString("")))
        }

    }

    fun countPlantPotNumbersOfLastGen(generations: MutableList<MutableList<Char>>): Int {
        val lastGen = generations[generations.lastIndex]

        return countPlantPotNumsOfGen(lastGen)
    }

    fun countPlantPotNumbers(generations: MutableList<MutableList<Char>>): Int {
        var plantCount = 0

        generations.forEach { gen ->

            plantCount += countPlantPotNumsOfGen(gen)
        }

        return plantCount
    }

    fun countPlantPotNumsOfGen(generation: MutableList<Char>): Int {

        var potCount = 0

        generation.forEachIndexed { idx, char ->
            if (char == '#') {
                potCount += (idx - 3)
            }
        }

        return potCount
    }

    fun countPlantedPots(generations: MutableList<MutableList<Char>>): Int {
        var plantCount = 0

        generations.forEach { gen ->
            gen.forEach { char ->
                if (char == '#') {
                    plantCount += 1
                }
            }
        }

        return plantCount
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
