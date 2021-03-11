package o1.blood


class BloodType(val abo: String, val rhesus: Boolean) {
  

  override def toString = {
    if (rhesus) {
      this.abo + "+"
    } else {
      this.abo + "-"
    }
  }
  
  def hasSafeABOFor(recipient: BloodType): Boolean = {
    (this.abo == "A" && recipient.abo == "A") ||
    (this.abo == "B" && recipient.abo == "B") ||
    ((this.abo == "A" || this.abo == "B" || this.abo == "AB") && recipient.abo == "AB") ||
    this.abo == "O"    
  }

  def hasSafeRhesusFor(recipient: BloodType) = {
    (this.rhesus && recipient.rhesus) || (this.rhesus == false)
  }
   
  def canDonateTo(recipient: BloodType) = {
    this.hasSafeABOFor(recipient) && this.hasSafeRhesusFor(recipient)
  }
    
  def canReceiveFrom(donor: BloodType) = {
    donor.canDonateTo(this)
  }
}

