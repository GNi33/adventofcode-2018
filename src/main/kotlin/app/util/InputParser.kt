package app.util

import app.model.DeserializableInputInterface
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class InputParser(mode: IInputReader.MODE = IInputReader.MODE.MAIN) {

    private val inputReader: IInputReader = InputReader(mode)

    fun splitInput(dayNumber: Int, delimiter: String): List<String> {

        val dayInputFile = inputReader.getFileForDay(dayNumber)
        val text = dayInputFile.readText()

        return text.split(delimiter)
    }

    fun <T : DeserializableInputInterface> parseInput(dtoClass: KClass<T>, input: String, delimiter: String): List<T> {

        val inputBlocks = input.trim().split(delimiter)

        return parseInputBlocks(dtoClass, inputBlocks)
    }

    fun <T : DeserializableInputInterface> parseInputBlocks(dtoClass: KClass<T>, inputBlocks: List<String>): List<T> {
        val instanceList = mutableListOf<T>()

        inputBlocks.forEach { block ->
            val instance = dtoClass.createInstance()
            instance.deserializeInput(block)
            instanceList.add(instance)
        }

        return instanceList
    }

}
