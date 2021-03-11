package o1.rainfall

import scala.io.StdIn._
import scala.collection.mutable._

object RainfallApp extends App {
  
  def rainfalls = {
     readLine("Enter rainfall (or 999999 to stop): ").toInt
  }     
    
  def rain = Stream.continually( rainfalls ).takeWhile( _ != 999999 )
  
  var accepted = Buffer[Int]()  
  
  def toAverage( number: Int) = accepted += number
  
  rain.foreach(toAverage)
  if (accepted.filter( _ >= 0).isEmpty) {
    println("No valid data. Cannot compute average.") 
  } else {
    println("The average is " + (accepted.sum / accepted.size) + ".")
  }
 
}