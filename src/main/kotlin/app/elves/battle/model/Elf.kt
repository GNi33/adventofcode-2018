package app.elves.battle.model

import app.model.Point

class Elf(pos: Point, actionOrder: Int) : Actor(pos, actionOrder) {
    override fun getSymbol(): Char {
        return 'E'
    }
}