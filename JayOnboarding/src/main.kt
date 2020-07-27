//Jay Dharmadhikari Kotlin Onboarding for Sushi Squad
//Comments have been added for requirements
import java.util.*
abstract class Item( name: String, price: DollarPrice){ // Abstract class
    val item_name = name
    val item_price = price
    fun getName(): String{
        return item_name
    }
    fun getDollarPrice(): DollarPrice {
        return item_price
    }
}
inline class DollarPrice(val price: Float) { //inline classes
    inline fun toRupees(): Float = price * 71.62f
}


class Weapon(name: String, price: DollarPrice) : Item( name, price)
class Potion( name: String, price: DollarPrice) : Item( name, price)

class Inventory { //inventory class
    val main_list = arrayOfNulls<Item>(16)
    var current_item_count = 0
    fun add_item(item: Item){
        if(current_item_count < 16) {
            main_list[current_item_count] = item
            current_item_count++
        }
        else println("Your inventory is full.")
    }
    fun print_inventory(dollars_or_rupees: Boolean){ //player can view inventory
        if(current_item_count == 0){
            println("Your inventory is empty.")
        }
        else {
            val weapon_list = main_list.filter { it is Weapon } //filter statement
            var potion_list = main_list.filter { it is Potion }
            println("Your weapons are:")
            for (i in weapon_list) { //for each loop
                print("\t" + i!!.getName() + ":")
                if (dollars_or_rupees) println(" " + i.getDollarPrice() + " dollars")
                else println(" " + i.getDollarPrice().toRupees() + " dollars")
            }
            println("Your potions are:")
            for (i in potion_list) {
                print("\t" + i!!.getName() + ":")
                if (dollars_or_rupees) println(" " + i.getDollarPrice() + " dollars")
                else println(" " + i.getDollarPrice().toRupees() + " dollars")
            }
        }

    }
}
var scanner_variable : Scanner = Scanner(System.`in`)
var player_inventory: Inventory = Inventory()
fun main() {
    println("Welcome to Jay's Inventory! Type 'help' for a list of all commands")
    var still_playing = true
    while(still_playing){
        print(">>> ")
        val command =  scanner_variable.next() //Process strings from console input
        when(command){ //when statement
            "help" -> println("'list' to list inventory, 'add' to add an item")
            "list" -> listPrices()
            "add" -> addItem()
            else -> println("Invalid input!")
        }
    }
}
fun listPrices(){ //player can view inventory
    println("List prices in dollars or rupees? (1 for dollars, 2 for rupees)")
    val command = scanner_variable.nextInt()
    when(command){
        1 -> player_inventory.print_inventory(true)
        2 -> player_inventory.print_inventory(false)
        else -> println("Invalid input!")
    }
}
fun addItem(){ //player can add items to inventory
    var item_to_add: Item
    println("Item name?")
    val item_name =scanner_variable.next()
    println("Item cost?")
    val item_price =scanner_variable.nextFloat()
    println("Is item a weapon or potion? (1 for weapon, 2 for potion)")
    val command = scanner_variable.nextInt()
    when(command){
        1 -> player_inventory.add_item(Weapon(item_name, DollarPrice(item_price)))
        2 -> player_inventory.add_item(Potion(item_name, DollarPrice(item_price)))
        else -> println("Invalid input!")
    }
    println("Item added!")

}
