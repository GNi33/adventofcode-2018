package app.elves.battle.model

import app.model.Point

abstract class Actor(val pos: Point, var actionOrder: Int) {

    abstract fun getSymbol(): Char

}
