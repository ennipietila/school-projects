package o1.viinaharava.gui

import scala.swing._
import o1.viinaharava._
import o1.gui.{BasicGridDisplay,O1SwingDefaults,Escapable}
import o1.gui.Dialog._
import o1.Pic
import o1.GridPos

////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it's not necessary    
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////


/** The singleton object `Viinaharava` represents the Viinaharava application. The object 
  * serves as an entry point for the game, and can be run to start up the user interface. 
  *
  * '''NOTE TO STUDENTS: In this course, you don't need to understand how this object works 
  * or can be used, apart from the fact that you can use this file to start the program.''' */
object Viinaharava extends SimpleSwingApplication with O1SwingDefaults {

  def top = new MainFrame with Escapable {
    this.title = "Viinaharava"
    this.location = new Point(50, 50) 
    this.menuBar = new MenuBar {
      contents += new Menu("Game") {
        contents += new MenuItem(Action("New 8 x 8") { startNewGame(createDefaultBoard) } )
        contents += new MenuItem(Action("New...")    { requestBoard().foreach( startNewGame(_) ) } )
        contents += new Separator
        contents += new MenuItem(Action("Exit")      { dispose() } )
      }
    }  
    this.resizable = false
    this.startNewGame(this.createDefaultBoard)
   
    def createDefaultBoard = new GameBoard(8, 8, 12)  
    
    def requestBoard() = {
      def askWidth() =      requestInt("Width of the board:",      _ > 0,  "Please enter a positive integer.",     RelativeTo(this))
      def askHeight() =     requestInt("Height of the board:",     _ > 0,  "Please enter a positive integer.",     RelativeTo(this)) 
      def askBoozeCount() = requestInt("Number of booze glasses:", _ >= 0, "Please enter a non-negative integer.", RelativeTo(this))
      for (width <- askWidth(); height <- askHeight(); boozes <- askBoozeCount()) yield new GameBoard(width, height, boozes) 
    }
    
    def startNewGame(board: GameBoard) = {
      val view = new ViinaharavaDisplay(board)
      view.update()
      this.contents = new FlowPanel(view)
      this.pack()
    }

  }
    
  
  private class ViinaharavaDisplay(val board: GameBoard) extends BasicGridDisplay[GameBoard, Glass](board, ViinaharavaDisplay.MaxSquareSize) {
    
    val RevealedBoozePic  = load("pictures/empty.png")
    val FullGlassPic      = load("pictures/full.png" )
    val RevealedWaterPics = (for (danger <- 0 to 8) yield load("pictures/" + danger + ".png")).toIndexedSeq
    
    private def load(picPath: String) = Pic.asImage(picPath).map( this.scale ).map( Array(_) ).getOrElse(Array.empty) 

    def missingElementVisuals = RevealedWaterPics(0)
    def elementVisuals(glass: Glass) = if (!glass.isEmpty) FullGlassPic else if (glass.isBooze) RevealedBoozePic else RevealedWaterPics(glass.dangerLevel)
    
    val popup = new Popup { 
      import PopupAction._
      this += new ElementAction("Drink",          AlwaysApplicable)( drink(_) )
      this += new ElementAction("Add booze here", AlwaysApplicable)(  _.pourBooze() )
    }

    def drink(glass: Glass) = {
      if (glass.drink()) {
        if (this.board.isOutOfWater) {
          this.update()
          display("You found all the water!", RelativeTo(this))
        }
      } else {
        this.update()
        display("Oh no! You struck booze.", RelativeTo(this))
      }
    }
    
    def elementClicked(glass: Glass) = {
      this.drink(glass)
    } 

  }
  
  private object ViinaharavaDisplay { 
    val MaxSquareSize = 100
  }
  
  
}  
  
