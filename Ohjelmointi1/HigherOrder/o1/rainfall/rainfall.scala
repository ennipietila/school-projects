package o1

package object rainfall {
  
  def averageRainfall(rainfall: Vector[Int]): Option[Int] = {
    var accepted = rainfall.takeWhile( _ < 999999 ).filter( _ >= 0 )
    if (accepted.size > 0) {
      Some(accepted.sum / accepted.size)
    } else {
      None
    }
  }

  
  def drySpell(rainfall: Vector[Int], length: Int) = {
    rainfall.sliding(length).map( _.takeWhile( _ >= 0 ).takeWhile( _ <= 5 ) ).indexWhere( _.length >= length )
      
    
  }
  

  
  
  
  
}