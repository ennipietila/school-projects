package o1.robots

import o1._

class Lovebot(name: String, body: RobotBody, val beloved: RobotBody) extends RobotBrain(name, body) {
  
  
  
  
  def moveBody() = {
    var (xdiff, ydiff) = this.location.diff(beloved.location)
        
    if (xdiff.abs + ydiff.abs > 1)  {
      if (xdiff.abs >= ydiff.abs) {
        if (xdiff > 0) {
          this.body.moveTowards(East)
        } else {
          this.body.moveTowards(West)
        }
      } else {
        if (ydiff > 0) {
          this.body.moveTowards(South)
        } else {
          this.body.moveTowards(North)
        }
      }
    }
  }
}

