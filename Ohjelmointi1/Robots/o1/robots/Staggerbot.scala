package o1.robots

import o1._
import o1.CompassDir
import scala.util.Random

class Staggerbot(name: String, body: RobotBody, randomSeed: Int) extends RobotBrain(name, body) {
  
  val generator = new Random(randomSeed)
  
  private def randomdirection = Vector(North, East, South, West)(generator.nextInt(4))
  
  def moveBody() = {
    if ( this.body.moveTowards(this.randomdirection) ) {
      this.body.spinTowards(randomdirection)
    }     
  }
}


