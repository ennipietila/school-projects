package o1.laskuri

// Tämä luokka liittyy lukuun 2.8 ja on esitelty siellä. 

class Laskuri(var arvo: Int) {
  
  def etene() = {
    this.arvo = this.arvo + 1
  }

  override def toString = "arvo " + this.arvo

}
