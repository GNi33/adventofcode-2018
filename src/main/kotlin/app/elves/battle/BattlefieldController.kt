package app.elves.battle

import app.elves.battle.model.Actor
import app.elves.battle.model.Battlefield
import app.elves.battle.model.Elf
import app.elves.battle.model.Goblin
import app.model.Point
import java.lang.Exception

class BattlefieldController(val battlefield: Battlefield) {

/*    val actors : MutableList<Actor> = mutableListOf()

    fun determineActionOrder() {
        actors.sortBy { it.pos }

        actors.forEachIndexed { index, actor ->
            actor.actionOrder = index + 1
        }
    }

    fun determineInRangeOfEnemy(actor: Actor) {
        val enemies = fetchEnemies(actor)

        enemies.forEach { println(it.getSymbol()) }
    }

    fun fetchEnemies(actor: Actor): List<Actor> {
        return actors.filter{ it.getSymbol() != actor.getSymbol() }
    }*/

    fun fetchMap(actors: List<Actor>, printOrder: Boolean = false): MutableList<String> {
        return battlefield.fetchMap(actors, printOrder)
    }

    fun printMap(actors: List<Actor>, printOrder: Boolean = false) {
        val output = fetchMap(actors, printOrder)

        output.forEach {
            println(it)
        }
        println("")
    }
}