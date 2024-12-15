package app.elves.battle.model

import app.model.Point

class Goblin(pos: Point, actionOrder: Int) : Actor(pos, actionOrder) {
    override fun getSymbol(): Char {
        return 'G'
    }
}
