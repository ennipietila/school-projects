package o1.people

class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]) {   

  def isAlive = yearOfDeath.isEmpty
  
  override def toString: String = {
    yearOfDeath match {
      case None => this.name + "(" + this.yearOfBirth + "-)"
      case Some(deathyear) => this.name + "(" + this.yearOfBirth + "-" + deathyear + ")"
    }
  }
}
