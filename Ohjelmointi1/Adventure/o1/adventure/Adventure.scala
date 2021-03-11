package o1.adventure


/** The class `Adventure` represents text adventure games. An adventure consists of a player and 
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very 
  * specific adventure game that involves a small trip through a twisted forest. All newly created 
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure 
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "Christmas shopping"
    
  private val middle        = new Area("Lobby", "You're standing in the lobby of your local mall.")
  private val bank          = new Area("Bank", "You're in the bank. You can withdraw some cash here to buy what you need.")
  private val gym           = new Area("Gym", "You're at the gym. Haven't you already done your workout today?\nBetter have a good reason to be here..")
  private val giftshop      = new Area("Gift Shop", "You see all kinds of nice things around you.\nWhat was it that you were supposed to buy..?")
  private val clothingStore = new Area("Clothing Store", "Oh no! You entered you're favorite store to shop in..\nYou'll never make it out of here in time. Game over :(")
  private val parking       = new Area("Parking Lot", "You've reached the parking lot but remember, you're not ready to go home yet.. you have some shopping to do!")
  private val destination   = parking    

       middle.setNeighbors(Vector("north" -> bank, "east" -> giftshop, "south" -> parking, "west" -> clothingStore, "up" -> gym   ))
         bank.setNeighbors(Vector(                                     "south" -> middle                                          ))
          gym.setNeighbors(Vector(                                                                             "down" -> middle   ))
     giftshop.setNeighbors(Vector(                                                         "west" -> middle                       ))
clothingStore.setNeighbors(Vector(                 "east" -> middle                                                               ))
      parking.setNeighbors(Vector("north" -> middle                                                                               ))

       gym.addItem(new Item("wallet", "It's your wallet"))
       
  giftshop.addItem(new Item("gift", "It's the perfect gift to give someone!"))
  giftshop.addItem(new Item("chocolate", "Yummm"))
  giftshop.addItem(new Item("socks", "There is always use for a good pair of socks!"))
  /** The character that the player controls in the game. */
  val player = new Player(middle)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 36

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.destination && this.player.has("gift") 

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */ 
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit || this.player.location == clothingStore

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are at the mall and in a hurry to buy your last christmas gift before the mall closes.\nYou realise you've lost your wallet.. you better find it quickly!"
    
  /** Returns a message that is to be displayed to the player at the end of the game. The message 
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "Nice! You've managed to find what you were looking for and made it to the parking area in time.\nYou can drive home with a smile on your face :)"
    else if (this.turnCount == this.timeLimit)
      "Oops! Time's up..  "
    else if (this.player.location == clothingStore) 
      clothingStore.description
    else  // game over due to player quitting
      "Quitter!" 
  }

  
  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual 
    * report of what happened, or an error message if the command was unknown. In the latter 
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) { 
      this.turnCount += 1 
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }
  
  
}

