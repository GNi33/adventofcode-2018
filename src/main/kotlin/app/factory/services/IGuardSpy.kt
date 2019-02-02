package app.factory.services

import app.factory.model.IGuard

interface IGuardSpy {

    fun sortInput(lines: List<String>) : List<String>

    fun parseInput(lines: List<String>) : List<IGuard>
}