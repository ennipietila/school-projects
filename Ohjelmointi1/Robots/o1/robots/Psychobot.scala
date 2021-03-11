package o1.robots

import o1._

class Psychobot(name: String, body: RobotBody) extends RobotBrain(name, body) {
  
  private def possibleRobot(direction: CompassDir) = {
    var pos = this.location.pathTowards(direction).find(!this.world.elementAt(_).isEmpty).get
    this.world.elementAt(pos)
  }
  
  def moveBody() = {
    val direction = Vector(North, East, South, West).find(n => this.possibleRobot(n) != Wall && this.possibleRobot(n).robot.get.isMobile)
    if (!direction.isEmpty) {
      val target = possibleRobot(direction.get).robot.get
      if (target.isMobile) {
        do {
          this.body.moveTowards(direction.get)
        } while (target.isMobile)
      }
    }
      
    
  }
  
}

