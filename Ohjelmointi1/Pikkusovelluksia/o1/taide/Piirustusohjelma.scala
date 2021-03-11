package o1.taide

import o1._

// Tämä koodi liittyy lukuun 2.8 ja on esitelty siellä. 

object Piirustusohjelma extends App {

  val teos = new Taideprojekti(rectangle(600, 600, White))

  val nakyma = new View(teos) {
    def makePic = teos.kuva

    // Tapahtumankäsittelykoodi puuttuu.
  }

  nakyma.start()
  
}

