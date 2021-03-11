package o1
object misc { // These definitions at the top are discussed in Chapter 4.5.

 def together(aanet: Vector[String], tempo: Int) = aanet.mkString("&") + "/" + tempo

 def tempo(song: String): Int = {
   if (song.contains("/")) {
     song.drop(song.indexOf("/") + 1).toInt
   } else 120
   
 }
  
  
  def toMinsAndSecs(secs: Int) = {
    val mins = secs / 60
    (mins, secs - mins * 60)
  }
  
  def isAscending(numbers: Vector[Int]) = {
    numbers.zip(numbers.tail).forall(isInOrder(_))
  }
  
  def isInOrder(pairOfNumbers: (Int, Int)) = pairOfNumbers._1 <= pairOfNumbers._2    // This example function is introduced in Chapter 8.4. You can ignore it until then.
  
}