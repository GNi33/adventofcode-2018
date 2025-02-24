package app.mine

import app.mine.model.Cart

class CartSimulation(testInput: List<String>, options: Map<String, Boolean> = mapOf()) {

    private val mapParser: MapParser = MapParser(testInput)
    private val options: Map<String, Boolean>
    private val crashes = mutableListOf<Pair<Int, Int>>()
    private val cartsToRemove = mutableListOf<Cart>()

    init {
        val defaultOptions = mutableMapOf(
            "printSimSteps" to false
        )

        defaultOptions.putAll(options)

        this.options = defaultOptions
    }

    fun runSimulation(stepNum: Int? = null) {
        val carts = mapParser.carts
        val map = mapParser.map

        if (options["printSimSteps"] == true) {
            map.printMap(carts)
        }

        if (stepNum != null) {
            runSimForSteps(carts, map, stepNum)
        } else {
            runSimUntilCrash(carts, map)
        }

        println("Crashes occurred at: " + crashes.distinct())
    }

    fun runSimulationUntilLastCart() {
        val carts = mapParser.carts
        val map = mapParser.map

        if (options["printSimSteps"] == true) {
            map.printMap(carts)
        }

        do {
            runStep(carts, map)
        } while(carts.size > 1)

        val lastCart = carts[0]

        println("Last cart is located at: " + Pair(lastCart.yPos, lastCart.xPos))
    }

    fun runSimUntilCrash(carts: MutableList<Cart>, map: app.mine.model.Map) {

        do {
            runStep(carts, map)
        } while (crashes.isEmpty())

    }

    fun runSimForSteps(carts: MutableList<Cart>, map: app.mine.model.Map, stepNum: Int) {
        repeat(stepNum) {
            runStep(carts, map)
        }
    }

    private fun runStep(carts: MutableList<Cart>, map: app.mine.model.Map) {

        carts.sortWith(compareBy({ it.xPos }, {it.yPos}))

        carts.forEach { cart ->
            cart.makeMove(map)
            checkForCrash(carts, cart)
        }

        if (cartsToRemove.size > 0) {
            cartsToRemove.forEach {
                carts.remove(it)
            }
        }

        if (options["printSimSteps"] == true) {
            println('\n')
            map.printMap(carts)
            println('\n')
        }
    }

    private fun checkForCrash(carts: List<Cart>, cart: Cart) {

        val filteredCarts = carts.filterNot { it == cart }

        val cartList = filteredCarts.filter { cart.xPos == it.xPos && cart.yPos == it.yPos }

        if (cartList.isNotEmpty()) {
            crashes.add(Pair(cart.yPos, cart.xPos))

            cartsToRemove.add(cart)
            cartList.forEach { cartsToRemove.add(it) }
        }
    }

    private fun checkForCrash(carts: List<Cart>): Boolean {

        carts.forEach { cart ->
            val cartList = carts.filter { cart.xPos == it.xPos && cart.yPos == it.yPos }

            if (cartList.size > 1) {
                crashes.add(Pair(cart.yPos, cart.xPos))
            }
        }

        return crashes.size == 0
    }

    fun getCrashList(): MutableList<Pair<Int, Int>> {

        if (crashes.size == 0) {
            throw RuntimeException("No crashes occurred")
        }

        return crashes
    }

}
