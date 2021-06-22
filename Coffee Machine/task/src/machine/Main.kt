package machine

data class Ingredients(val water: Int, val milk: Int, val coffee: Int,
                       val dispcup: Int,  var money: Int) {
    // div : Int
    operator fun div (ing: Ingredients): Int {
        val outWater = water / ing.water
        val outMilk = milk / ing.milk
        val outCoffee = coffee / ing.coffee
        val outDispCup = dispcup / ing.dispcup
        var out = if (outWater < outMilk) outWater else outMilk
        out = if (out < outCoffee) out else outCoffee
        out = if (out < outDispCup) out else outDispCup
        return out
    }
    // minus (add money)
    operator fun minus (ing: Ingredients): Ingredients {
        val outWater = water - ing.water
        val outMilk = milk - ing.milk
        val outCoffee = coffee - ing.coffee
        val outDispCup = dispcup - ing.dispcup
        val outMoney = money + ing.money
        return Ingredients(outWater, outMilk, outCoffee, outDispCup, outMoney)
    }
    // plus (without money)
    operator fun plus (ing: Ingredients): Ingredients {
        val outWater = water + ing.water
        val outMilk = milk + ing.milk
        val outCoffee = coffee + ing.coffee
        val outDispCup = dispcup + ing.dispcup
        val outMoney = money + ing.money
        return Ingredients(outWater, outMilk, outCoffee, outDispCup, outMoney)
    }
    // <>
    operator fun compareTo(ing: Ingredients): Int {
        if (water < ing.water) return -1
        else if (milk < ing.milk) return -2
        else if (coffee < ing.coffee) return -3
        else if (dispcup < ing.dispcup) return -4
        else return 1
    }
    // times
    operator fun times (time: Int): Ingredients {
        val outWater = time * water
        val outMilk = time * milk
        val outCoffee = time * coffee
        val outDispCup = time * dispcup
        val outMoney = time * money
        return Ingredients(outWater, outMilk, outCoffee, outDispCup, outMoney)
    }
    // toString
    override fun toString(): String =
        "$water of water\n"+
        "$milk of milk\n"+
        "$coffee of coffee beans\n"+
        "$dispcup of disposable cups\n"+
        "$money of money"

}

fun getInt(vText: String): Int {
    print(vText)
    val retInt = readLine()!!.toInt()
    return retInt
}

class CoffeeMachine() {
    var onBoard = Ingredients(0, 0, 0,0, 0)
    val espresso = Ingredients(250, 0, 16, 1,4)
    val latte = Ingredients(350, 75, 20, 1,7)
    val cappuccino = Ingredients(200, 100, 12, 1,6)

    fun takeMoney() {
        println("I gave you \$${onBoard.money}")
        onBoard.money = 0
    }

    fun fill () {
        val ing = Ingredients(
            getInt("Write how many ml of water the coffee machine has: "),
            getInt("Write how many ml of milk the coffee machine has: "),
            getInt("Write how many grams of coffee beans the coffee machine has: "),
            getInt("Write how many disposable cups of coffee do you want to add: "),
            0
        )
        onBoard += ing
    }

    fun buyCup() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val type = readLine()!!
        var err = "Sorry, not enough "
        when (makeCup(type)) {
            1 -> println("I have enough resources, making you a coffee!")
            10 -> return
            -1 -> println(err+"water!")
            -2 -> println(err+"milk!")
            -3 -> println(err+"coffee beans!")
            -4 -> println(err+"disposable cups!")
        }
    }

    fun testCup(ing: Ingredients): Int {
        var test = 0
        test = onBoard.compareTo(ing)
        if (test > 0)
            onBoard -= ing
        return test
    }

    fun makeCup(type: String): Int {
        var compare = 0
        when (type) {
            "1" -> compare = testCup(espresso)
            "2" -> compare = testCup(latte)
            "3" -> compare = testCup(cappuccino)
            "back" -> return 10
        }
        return compare
    }

    fun getStat() {
        println("The coffee machine has:")
        println(onBoard)
    }

    fun getMenu () {
        while (true) {
            print("Write action (buy, fill, take, remaining, exit): ")
            var action = readLine()!!
            when (action) {
                "buy" -> buyCup()
                "fill" -> fill()
                "take" -> takeMoney()
                "remaining" -> getStat()
                "exit" -> return
            }
            println("")
        }
    }

}

fun main() {
    val cm = CoffeeMachine()
    // base init
    cm.onBoard += Ingredients(400, 540, 120, 9, 550)
    // menu
    cm.getMenu()
}
