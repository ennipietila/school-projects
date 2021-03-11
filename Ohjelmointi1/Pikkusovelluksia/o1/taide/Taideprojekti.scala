package o1.taide

import o1._

// Tämä koodi liittyy lukuun 2.8 ja on esitelty siellä. 

class Taideprojekti(tausta: Pic) {
  var kuva = tausta                   // kokooja
  var pensseli = circle(10, Black)    // tuoreimman säilyttäjä

  def piirra(mihin: Pos) = {
    // Sijoita kuva-muuttujalle uusi arvo, joka saadaan asemoimalla sen aiemman 
    // arvon päälle pensselin kuva mihin-parametrin osoittamaan kohtaan. 
  }
}


