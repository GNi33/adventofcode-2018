package app.device.plants

class PlantPotSimulator(input: List<String>) {
    private val inputParser = PlantInputParser(input)
    private val rulePipe = RulePipe(inputParser.rules)

    fun simulateGenerations(genCount: Int = 10) {
        val initialState = inputParser.initialState
        val generations = mutableListOf<MutableList<Char>>()

        generations.add(initialState)

        var newGen = initialState

        for (i in 0 until genCount+1) {

            newGen = simulateGeneration(newGen)

            generations.add(newGen)

        }

    }

    fun simulateGeneration(plantInput: MutableList<Char>): MutableList<Char> {

        println(plantInput.joinToString(""))

        return rulePipe.transform(plantInput)
    }
}
