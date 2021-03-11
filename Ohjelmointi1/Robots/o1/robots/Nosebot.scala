package o1.robots

import o1._

class Nosebot(name: String, body: RobotBody) extends RobotBrain(name, body) {

  def attemptMove() = { 
    if (this.moveCarefully) {
      true 
    } else {
      this.body.spinClockwise
      false
    }
  }

  def moveBody() = {
    val moves = Stream.continually(attemptMove).take(4).find( _ == true )  
    if (!moves.isEmpty) moves.get
  }



}


