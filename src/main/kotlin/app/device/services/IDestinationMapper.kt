package app.device.services

import app.model.Point

interface IDestinationMapper {
    fun getLargestAreaSize(): Int
    fun parseStringInput(input: List<String>): Map<String, Point>
}
