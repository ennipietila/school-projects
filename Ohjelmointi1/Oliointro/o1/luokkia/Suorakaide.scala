package o1.luokkia
import o1._

// Tämä luokka liittyy lukuun 2.4 ja on esitelty siellä. 

class Suorakaide(val sivu1: Double, val sivu2: Double) {

  def ala = this.sivu1 * this.sivu2

  def kuvaksi(vari: Color) = rectangle(sivu1, sivu2, vari)

}

class VarillinenSuorakaide(val sivu1: Double, val sivu2: Double, val vari: Color) {

  def ala = this.sivu1 * this.sivu2

  def kuvaksi = rectangle(this.sivu1, this.sivu2, vari)

}