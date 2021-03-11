package o1.laskuri

import o1._

// Tämä koodi liittyy lukuun 2.8 ja on esitelty siellä. 

object Tikitysohjelma extends App {
  val tikityslaskuri = new Laskuri(0)
  val tausta = rectangle(500, 500, Black)

  val nakyma = new View(tikityslaskuri, 50) {
    def makePic = tausta.place(square(tikityslaskuri.arvo, White).clockwise(tikityslaskuri.arvo), new Pos(250, 250))

    override def onTick() = {
      tikityslaskuri.etene()
      
    }
  }

  nakyma.start()
}
