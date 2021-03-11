package o1.auctionhouse

import o1._ 

class FixedPriceSale(val description: String, val price: Int, duration: Int) {
  
  private var daysleft: Int = this.duration
  
  override def toString = this.description
 
  private var ostaja: Option[String] = None
  
  def buyer = this.ostaja
      
  def buy(buyer: String): Boolean = {
    if (this.isOpen) {
      this.ostaja = Some(buyer)
      true
    } else false
    
  }  
    
  def isExpired = this.daysleft <= 0
  
  def isOpen: Boolean = {
    ostaja match {
      case Some(ostaja) => false
      case None => if (this.isExpired) false else true
    }
  }
  
  def advanceOneDay() = {
    if (this.isOpen) {
      this.daysleft = this.daysleft - 1
    }  
  }
  
  def daysLeft: Int = this.daysleft
      
  
  
  
}