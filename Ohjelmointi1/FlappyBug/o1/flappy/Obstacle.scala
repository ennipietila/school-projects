package o1.flappy

import o1._

// This class is introduced in Chapter 2.4.

class Obstacle(val radius: Int) { 
  
  import scala.util.Random
  
  private def randomLaunchPosition() = {
    val launchX = 1000 + this.radius + Random.nextInt(500)
    val launchY = Random.nextInt(400)
    new Pos(launchX, launchY)
  }
  val initialPos = this.randomLaunchPosition()
  private var currentPos = this.initialPos
  def pos = this.currentPos

  def approach() = {
    if (this.isActive) {
      this.currentPos = this.currentPos.addX(-ObstacleSpeed)
    } else {
      this.currentPos = this.randomLaunchPosition()
    }
  }

  override def toString = "center at " + this.pos + ", radius " + this.radius
  
  def touches(otokka: Bug) = this.pos.distance(otokka.pos) < (this.radius + otokka.radius)
    
  def isActive = this.currentPos.x >= -this.radius
  

  
}
