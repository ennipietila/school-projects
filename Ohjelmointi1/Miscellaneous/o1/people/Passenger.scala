package o1.people


class Passenger(val name: String, val card: Option[TravelCard]) {
  
  
  def hasCard = {
    card match {
      case None => false
      case Some(kortti) => true
    }
  }

  def canTravel: Boolean = {
    card match {
      case None => false
      case Some(kortti) => kortti.isValid 
    }
    
  }
  
}