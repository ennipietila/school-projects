package o1.looptest

// This program is associated with Chapter 5.4.

import scala.math._

object Task7 extends App {

  // Examine the following program and try running it. It outputs an approximate sine curve.
  // Do you understand how the program works?
  // 
  // Try adjusting the numerical literals used below. How do your changes impact on the "image"?
  // 
  // For a small handful of points, submit any modified version of the given program that 
  // uses different numbers than the original.  

  for (number <- 0.0 to (2 * Pi) by (Pi / 16)) {
    val scaledSine = (sin(number) * 40).round.toInt   
    println("*" * (36 + scaledSine))      
  }


}

