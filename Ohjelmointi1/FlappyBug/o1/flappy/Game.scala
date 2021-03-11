package o1.flappy

import o1._

class Game {
  val bug = new Bug(new Pos(100, 40))
  val obstacles = Vector(new Obstacle(70), new Obstacle(30), new Obstacle(20))
  
  def timePasses() = {
    bug.fall()
    this.obstacles.foreach( _.approach() )
  }
  def activateBug() = {
    bug.flap(15)
  }
  def isLost = {
    var foundTouching = false
    var touchingBug = this.obstacles.exists(_.touches(this.bug) && foundTouching == false) 
    touchingBug || !(this.bug.isInBounds)   
  }
  
  
}

  // Your code goes here. Please add only what is requested by the ebook. To avoid 
  // confusing our automatic assessment system, please don't invent additions of your own 
  // here (at least not until you're done with the ebook’s official FlappyBug assignments).  

  
  

