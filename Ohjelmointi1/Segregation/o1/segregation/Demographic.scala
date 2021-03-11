package o1.segregation

import o1.Color


sealed trait Demographic {
  
}

class Occupied(val label: Color) extends Demographic {
  override def toString = s"occupied by the $label demographic" 
}


object Vacant extends Demographic {
  override def toString = "vacant residence"
}
  
