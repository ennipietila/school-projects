package o1.odds

// This class is gradually developed between Chapters 2.4 and 3.3.

class Odds(val wont: Int, val will: Int) {
  
  def probability = 1.0 * this.will / (this.wont + this.will)
  def fractional = this.wont + "/" + this.will
  def decimal = 1.0 / probability
  def winnings(bet: Double) = bet * decimal
  def not = new Odds(this.will, this.wont)
  override def toString = fractional
  def both(joku: Odds) = {
    val osoittaja = this.wont * joku.wont + this.wont * joku.will + this.will * joku.wont
    val nimittaja = this.will * joku.will
    val molemmat = new Odds(osoittaja, nimittaja)
    molemmat
  }
  def either(toinen: Odds) = {
    val osoittaja2 = this.wont * toinen.wont
    val nimittaja2 = this.wont * toinen.will + this.will * toinen.wont + this.will * toinen.will
    val jompikumpi = new Odds(osoittaja2, nimittaja2)
    jompikumpi
  }
  def isLikely = this.wont < this.will
  def isLikelierThan(another: Odds) = this.probability > another.probability
  def moneyline = {
    if (this.probability <= 0.5) {
      100 * this.wont / this.will
    } else {
      -100 * this.will / this.wont
    }
  }
}
//(a1*b1+a1*b2+a2*b1)/(a2*b2) both
//(a1*b1)/(a1*b2+a2*b1+a2*b2) either