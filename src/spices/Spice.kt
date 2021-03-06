package spices

fun main(){
    val curry: Curry = Curry("non-spicy")
    curry.prepareSpice()
    curry.spiceDetails()
    println("The color of the curry is: ${curry.spiceColor.color}")
}

//an abstract class Spice
private abstract class Spice: SpiceColor {
    abstract val spiceName: String
    abstract val spiciness: String
    abstract val heatLevel: Int
    abstract val spiceColor: SpiceColor

    abstract fun spiceDetails()
    abstract fun prepareSpice()
}


//Curry class that implements Spice as it is one of the spices and Grinder
//as it needs to be grounded to turn it into a powder
private class Curry(override val spiciness: String, override val spiceColor: SpiceColor = YellowSpiceColor): Spice(), Grinder by Grind,
                    SpiceColor by spiceColor{

    override val spiceName: String
        get() = "curry"

    override val heatLevel:Int
        get() {
            return when(spiciness){
                "non-spicy" -> 0
                "mild" -> 1
                "spicy" -> 2
                "hot" -> 3
                else -> 1
            }
        }

    override fun spiceDetails() {
        println("Spice name: $spiceName" +
                "\nSpiciness: $spiciness" +
                "\nHeat: $heatLevel\n")
    }

    override fun prepareSpice() {
        Grind.grind(spiceName)
    }
}


//interface Grinder to grind objects
private interface Grinder{
    fun grind(objectName: String)
}

//@Grind used as interface delegation in curry class
private object Grind: Grinder {
    override fun grind(objectName: String) {
        println("Grinding $objectName...")
    }
}

//interface for color of Spices
private interface SpiceColor {
    val color: String
}

//singleton subclass for YellowColor of spices
object YellowSpiceColor: SpiceColor {
    override val color = "YellowSpiceColor"
}