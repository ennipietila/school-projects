package o1.flappy

import o1._

class Bug(initialPos: Pos) {
  private var currentPos = initialPos
  def pos = currentPos
  val radius = 15
  
  override def toString = "center at " + this.pos + ", radius " + radius
  
  private var yVelocity = 0.0
  
  def fall() = {
    if (this.currentPos.y < 350) {
      this.yVelocity = this.yVelocity + 2
      move(this.yVelocity)
    }
  }
  
  def flap(nopeus: Double) = {
    this.yVelocity = this.yVelocity - nopeus
    
  }
  
  def move(lisäys: Double) = {
    this.currentPos = this.currentPos.addY(lisäys).clampY(0, 350)
  }
  
  def isInBounds: Boolean = {
    this.pos.y > 0 && this.pos.y < 350
  }
}

// Define class Bug here.