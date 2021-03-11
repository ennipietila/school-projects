package o1.flappy.gui

import o1._
import o1.flappy._

// This class is introduced in Chapter 2.7.

object FlappyBugApp extends App {

  val sky        = rectangle(ViewWidth, ViewHeight,  LightBlue)
  val ground     = rectangle(ViewWidth, GroundDepth, SandyBrown)
  val trunk      = rectangle(30, 250, SaddleBrown)
  val foliage    = circle(200, ForestGreen) 
  val tree       = trunk.onto(foliage, TopCenter, Center)
  val rootedTree = tree.onto(ground, BottomCenter, new Pos(ViewWidth / 2, 30))
  val scenery    = sky.place(rootedTree, BottomLeft, BottomLeft)

  
  val bugPic = Pic("ladybug.png")


  def rockPic(obstacle: Obstacle) = circle(obstacle.radius * 2, Black)

  
  val game = new Game
  val gui = new View(game, "FlappyBug") {
    def makePic = {
      var kuva = game.obstacles.foldLeft(background)( (kuva, current) => kuva.place(rockPic(current), current.pos) )     
      kuva.place(bugPic, game.bug.pos)
    }
    
    override def onKeyDown(painettu: Key) = {
      if (painettu == Key.Space) {
         game.activateBug()
      }
    }
    override def onTick() = {
      game.timePasses()
      this.background = this.background.shiftLeft(2)
    }
    var background = scenery
    override def isDone = game.isLost
  
  }
 
  gui.start()
                   
                   
}

