package app.elves.recipes

import app.elves.Elf

class RecipeScoreCalculator {

    private val elves: List<Elf> = listOf(Elf(0), Elf(1))

    fun run() {

        val words = "The quick brown fox jumps over the lazy dog".split(" ")
        //convert the List to a Sequence
        val wordsSequence = words.asSequence()

        val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

        println("Lengths of first 4 words longer than 3 chars")
        // terminal operation: obtaining the result as a List
        println(lengthsSequence.toList())

        val test = sequence {
            yield(1)
        }

        println(test.take(1).toList())

    }

}
