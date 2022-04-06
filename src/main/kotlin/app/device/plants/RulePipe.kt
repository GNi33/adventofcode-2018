package app.device.plants

import app.device.model.plants.Rule
import java.util.*

class RulePipe(private val rules: LinkedList<Rule>) {

    fun transform(plantInput: MutableList<Char>): MutableList<Char> {
        val newGen = MutableList<Char>(size = plantInput.size, init = { _ -> '.' })

        plantInput.forEachIndexed plantLoop@{ idx, _ ->

            val slidingWindow = getSlidingWindow(idx, plantInput)

            rules.forEach { rule ->
                if (rule.applies(slidingWindow)) {
                    newGen[idx] = rule.result

                    if (rule.result == '#' && idx == plantInput.size - 1) {
                        newGen.add(plantInput.size, '.')
                    }
                }
            }
        }

        return newGen
    }

    private fun getSlidingWindow(idx: Int, plantInput: MutableList<Char>) : List<Char> {

        val firstElem = if(idx - 2 < 0) { '.' } else { plantInput[idx-2] }
        val secondElem = if(idx - 1 < 0) { '.' } else { plantInput[idx-1] }
        val thirdElem = plantInput[idx]
        val fourthElem = if(idx + 1 > plantInput.size-1) { '.' } else { plantInput[idx+1] }
        val fifthElem = if(idx + 2 > plantInput.size-1) { '.' } else { plantInput[idx+2] }

        return listOf(firstElem, secondElem, thirdElem, fourthElem, fifthElem)
    }
}
