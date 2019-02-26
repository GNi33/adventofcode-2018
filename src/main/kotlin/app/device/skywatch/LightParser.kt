package app.device.skywatch

import app.device.model.sky.SkyLight

class LightParser(input: List<String>) {

    val lights : List<SkyLight>

    init {
        lights = parseLights(input)
    }

    private fun parseLights(input: List<String>) : List<SkyLight> {
        return input.map {
            SkyLight.create(it)
        }
    }

}