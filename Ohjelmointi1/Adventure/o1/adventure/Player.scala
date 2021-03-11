package o1.adventure

import scala.collection.mutable.Map
import scala.collection.mutable.Buffer
  
/** A `Player` object represents a player character controlled by the real-life user of the program. 
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  
  
  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  
  /** Returns the current location of the player. */
  def location = this.currentLocation
  
  
  /** Attempts to move the player in the given direction. This is successful if there 
    * is an exit from the player's current location towards the direction name. 
    * Returns a description of the results of the attempt. */
  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation) 
    if (destination.isDefined) "You go " + direction + "." else "You can't go " + direction + "."
  }

  
  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = {
    "You rest for a while. Better get a move on, though." 
  }
  
  
  /** Signals that the player wants to quit the game. Returns a description of what happened within 
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }

  
  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name  
  
  private val items = Map[String, Item]()
  
  private val wallet = Buffer("creditcard", "id")
  
  def inventory: String = {
    if (this.items.isEmpty) {
      "Your bag is empty."
    } else {
      "Your bag contains:\n" + items.keys.mkString("\n")
    }
    
  }
  
  def has(itemName: String) = {
    this.items.get(itemName).isDefined
  }
  
  
  
  def buy(itemName: String): String = {
    if (this.hasCash && this.location.isGiftshop) {
      this.location.findItem(itemName).foreach( this.items += itemName -> _ )
      this.wallet -= "cash"
      "You buy the " + itemName + "."
    } else {
      "You need cash to buy anything at this mall."
    }
  }
  
  
  def get(itemName: String): String = {  
    if (this.location.contains("wallet")) { 
      this.location.removeItem("wallet").foreach( this.items += itemName -> _ )
      "You pick up the " + itemName + "."
    } else {
      "There is nothing here to pick up."       
    }    

  }  
  
  
  def hasCash = this.wallet.contains("cash")
    
  def withdraw() = {
    if (this.location.isBank && this.has("wallet")) {  
      wallet += "cash"
      "Good, you have cash now. It is in your wallet."
    } else if (this.location.isBank && !this.has("wallet")) {
      "You have to find your wallet first..."
    } else {
      "You need to go to the bank to withdraw cash!"
    }
  }
  
  def examine(itemName: String): String = {
    if (this.has(itemName) && itemName != "wallet") {
      items(itemName).description    
    } else if (this.has(itemName) && itemName == "wallet") {
      items(itemName).description + "\nIt contains:\n" + this.wallet.mkString("\n")
    } else {
      "If you want to examine something, you need to pick it up first."
    }
  }
  
  
  
  def help = {
    "Use command 'withdraw' in the bank to get cash.\nUse command 'buy' to get something from the shop."
  }
}


