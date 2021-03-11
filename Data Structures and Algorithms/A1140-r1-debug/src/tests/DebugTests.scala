package tests
import org.scalatest._
import timer._
import debug._

class FindPairSpec extends FlatSpec with Matchers {
  "Your findPairFast" should "give the same results as findPairSlow" in {
    val nofLists = 10
    val nofTests = 100
    val N = 100
    val rand = new scala.util.Random
    for(l <- 1 to nofLists){
      val list = (1 to N).map(i => rand.nextInt()).sorted.toList
      for(t <- 1 to nofTests){
        val randInt = rand.nextInt()
        val insideList = list(rand.nextInt(N)) + list(rand.nextInt(N))
        val result1: Option[(Int, Int)] = findPairFast(list, randInt)
        val result2: Option[(Int, Int)] = findPairFast(list, insideList)
        val correct1 = findPairSlow(list, randInt)
        val correct2 = findPairSlow(list, insideList)
        result1 should be (correct1)
        result2 should be (correct2)
      }
    }
  }

  it should "be at least hundred times faster than findPairSlow with mediam-size List input" in {
    val rand = new scala.util.Random
    val N = 10000
    val nofTests = 10
    var cumulativeSlow = 0.0
    var cumulativeFast = 0.0
    println("Running performance tests: findPairFast on List input")
    for(t <- 1 to nofTests){
      val list = (1 to N).map(x => rand.nextInt()).sorted.toList
      val value = list(rand.nextInt(N)) + list(rand.nextInt(N))
      val (result, timeFast) = measureCpuTime { findPairFast(list, value) }
      val (correct, timeSlow) = measureCpuTime { findPairSlow(list, value) }
      println(" Test "+t)
      println(f"  fast vs slow: $timeFast%.3g vs $timeSlow%.3g")
      cumulativeSlow += timeSlow
      cumulativeFast += timeFast
      result should be (correct)
    }
    println(f" Total time: $cumulativeFast%.3g vs $cumulativeSlow%.3g")
    val speedup = cumulativeSlow / cumulativeFast
    println(f" Speedup: $speedup%.3f")
    speedup should be >= (100.0)
  }
}

class BinarySearchSpec extends FlatSpec with Matchers {
  "Your binarySearch" should "terminate on some random input" in {
    val rand = new scala.util.Random
    val N = 100
    val nofTests = 100
    println("Running termination tests on binary search")
    for(t <- 1 to nofTests) {
      val a = (0 until N).toArray
      val result: Option[Int] = binarySearch(a, rand.nextInt(N))
    }
  }
  
  it should "be hundred times faster than indexOf" in {
    val rand = new scala.util.Random
    val N = 10000000
    val nofTests = 10
    val a = (0 until N).toArray
    var cumuBinarySearch = 0.0
    var cumuIndexOf = 0.0
    println("Running performance tests on binary search")
    for(t <- 1 to nofTests){
      val target = rand.nextInt(N)
      val (result, timeBS) = measureCpuTime { binarySearch(a, target) }
      val (correct, timeIO) = measureCpuTime { a.indexOf(target) }
      println(" Test "+t)
      println(f"  BinarySearch vs IndexOf: $timeBS%.3g vs $timeIO%.3g")
      cumuBinarySearch += timeBS
      cumuIndexOf += timeIO
      if(result.isEmpty) correct should be (-1)
      else result.get should be (correct)
    }
    println(f" Total time: $cumuBinarySearch%.3g vs $cumuIndexOf%.3g")
    val speedup = cumuIndexOf / cumuBinarySearch
    println(f" Speedup: $speedup%.3f")
    speedup should be >= (100.0)
  }
}

class BoxBlurSpec extends FlatSpec with Matchers {
  "Your boxBlur" should "work correctly on a simple input" in {
    // Sequence of tests cases of form (rows, columns, dim, input, correct)
    val testCases: Seq[(Int,Int,Int,Array[Double],Array[Double])] = Seq(
      (3, 3, 1,
       Array[Double](0.0, 1.0, 3.0,
                     0.0, 0.0, 1.0,
                     1.0, 1.0, 2.0),
       Array[Double](0.0, 1.0, 3.0, 
                     0.0, 1.0, 1.0, 
                     1.0, 1.0, 2.0)
     )
     ,(5, 5, 1,
       Array[Double](0.0, 1.0, 3.0, 3.0, 2.0,
                     0.0, 0.0, 1.0, 4.0, 2.0,
                     1.0, 1.0, 2.0, 3.0, 7.0,
                     0.0, 1.0, 3.0, 3.0, 2.0,
                     0.0, 1.0, 0.0, 4.0, 3.0),
       Array[Double](0.0, 1.0, 3.0, 3.0, 2.0, 
                     0.0, 1.0, 2.0, 3.0, 2.0, 
                     1.0, 1.0, 2.0, 3.0, 7.0, 
                     0.0, 1.0, 2.0, 3.0, 2.0, 
                     0.0, 1.0, 0.0, 4.0, 3.0)
     )
     ,(5, 5, 2,
       Array[Double](0.0, 1.0, 3.0, 3.0, 2.0,
                     0.0, 0.0, 1.0, 4.0, 2.0,
                     1.0, 1.0, 2.0, 0.0, 2.0,
                     0.0, 0.0, 1.0, 4.0, 2.0,
                     2.0, 1.0, 1.0, 3.0, 5.0),
       Array[Double](0.0, 1.0, 3.0, 3.0, 2.0, 
                     0.0, 0.0, 1.0, 4.0, 2.0, 
                     1.0, 1.0, 1.64, 0.0, 2.0, 
                     0.0, 0.0, 1.0, 4.0, 2.0, 
                     2.0, 1.0, 1.0, 3.0, 5.0)
     )
    )
    for((rows, columns, dim, input, correct) <- testCases) {
      withClue(f"On (${input.mkString(",")}) with rows=$rows, columns=$columns, and dim=$dim:") {
        val result = boxBlur(input, rows, columns, dim)
        for(r <- 0 until rows; c <- 0 until columns) {
          withClue("At position ("+r+","+c+"):") {
            result(r*columns + c) should be (correct(r*columns + c))
          }
        }
      }
    }
  }
  it should "not modify the input array" in {
    val rand = new scala.util.Random
    val rows = 10
    val columns = 10
    val dim = 2
    val nofTests = 10
    val n = (1 to rows*columns)
    for(t <- 1 to nofTests) {
      val original = n.map(x => rand.nextInt(20).toDouble)
      val a = original.toArray
      boxBlur(a, rows, columns, dim)
      for(i <- 0 until a.size)
        a(i) should be (original(i))
    }
  }
}

