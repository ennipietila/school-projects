package tests
import org.scalatest._

import scala.util.Random
import radixSort._

class LSDRadixSortSpec extends FlatSpec with Matchers {
  val rand = new scala.util.Random

  "The lsdRadixSort method" should "work correctly" in {
    val nofTests = 100
    for(t <- 1 to nofTests) {
      val N = rand.nextInt(40)+3
      val a = Seq.tabulate[Int](N)(j => rand.nextInt(Int.MaxValue)).toArray
      val sorted = a.sorted
      lsdRadixSort(a)
      for(i <- 0 until N) {a(i) should be (sorted(i))}
    }
  }


  it should "be at least two times faster than the sorted-method" in {
    val nofTests = 10
    val N = 1000000
    val M = 23
    var cumuStandard = 0.0
    var cumuRadix = 0.0
    val a = new Array[Int](N)
    val reference = new Array[Int](N)
    for(t <- 1 to nofTests) {
      for(i <- 0 until N) {reference(i) = rand.nextInt(Int.MaxValue); a(i) = reference(i) }
      val (sorted, t1) = timer.measureCpuTime {reference.sorted }
      val (dummy, t2) = timer.measureCpuTime {lsdRadixSort(a) }
      cumuStandard += t1
      cumuRadix += t2
    }
    val avgStandard = cumuStandard / nofTests
    val avgRadix = cumuRadix / nofTests
    println(f"sorted vs radix: $avgStandard%.3g vs $avgRadix%.3g")
    Console.out.flush()
    avgRadix should be < (0.5 * avgStandard)
  }

  /**
   * A helper for comparing java.util.Arrays.sort and the radix sort.
   */
  private def compareSortAndRadix(nofTests: Int, N: Int) = {
    require(nofTests > 0)
    require(N > 0)
    var sortCumu = 0.0
    var radixCumu = 0.0
    val a = new Array[Int](N)
    val reference = new Array[Int](N)
    for(t <- 1 to nofTests) {
      for(i <- 0 until N) {reference(i) = rand.nextInt(Int.MaxValue); a(i) = reference(i) }
      val (dummy1, t1) = timer.measureCpuTime {java.util.Arrays.sort(reference) }
      val (dummy2, t2) = timer.measureCpuTime {lsdRadixSort(a) }
      (0 until N).foreach(i => a(i) should be (reference(i)))
      sortCumu += t1
      radixCumu += t2
    }
    (sortCumu / nofTests, radixCumu / nofTests)
  }

  /**
   * A helper for comparing java.util.Arrays.sort and the radix sort.
   */
  private def compareSortAndRadixFew(nofTests: Int, N: Int, values: IndexedSeq[Int]) = {
    require(nofTests > 0)
    require(N > 0)
    require(values.nonEmpty)
    var sortCumu = 0.0
    var radixCumu = 0.0
    for(t <- 1 to nofTests) {
      val a = Seq.tabulate[Int](N)(i => values(rand.nextInt(values.size))).toArray
      val b = a.clone()
      val (dummy1, t1) = timer.measureCpuTime {java.util.Arrays.sort(a) }
      val (dummy2, t2) = timer.measureCpuTime {lsdRadixSort(b) }
      sortCumu += t1
      radixCumu += t2
    }
    (sortCumu / nofTests, radixCumu / nofTests)
  }

  /*
   * Performance tests for smallish integer arrays.
   */
  it should s"be faster than the java.util.Arrays.sort-method on smallish arrays with random integers" in {
    val N = 10000
    val nofTests = 100
    val (sortAvg, radixAvg) = compareSortAndRadix(nofTests, N)
    println(f"java.util.Arrays.sort vs radix: $sortAvg%.3g vs $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be < (1.0 * sortAvg)
  }

  it should s"be at most 3 times slower than the java.util.Arrays.sort-method on smallish arrays with integers drawn from a small set" in {
    val N = 10000
    val nofTests = 100
    val M = 21
    val values = IndexedSeq.tabulate[Int](M)(j => rand.nextInt(Int.MaxValue))
    val (sortAvg, radixAvg) = compareSortAndRadixFew(nofTests, N, values)
    println(f"java.util.Arrays.sort vs radix: $sortAvg%.3g vs $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be <= (3.0 * sortAvg)
  }

  it should s"be faster than the java.util.Arrays.sort-method on smallish arrays with small integers" in {
    val N = 10000
    val nofTests = 100
    val M = 21
    val values = IndexedSeq.tabulate[Int](M)(i => i)
    val (sortAvg, radixAvg) = compareSortAndRadixFew(nofTests, N, values)
    println(f"java.util.Arrays.sort vs radix: $sortAvg%.3g vs $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be < (1.0 * sortAvg)
  }

  /*
   * Performance tests for larger integer arrays.
   */
  it should s"be faster than the java.util.Arrays.sort-method on large arrays with random integers" in {
    val N = 5000000
    val nofTests = 10
    val (sortAvg, radixAvg) = compareSortAndRadix(nofTests, N)
    println(s"Averages of $nofTests arrays with $N random integers:")
    println(f"  java.util.Arrays.sort: $sortAvg%.3g")
    println(f"  radix sort:            $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be < (1.0 * sortAvg)
  }

  it should s"be at most 3 times slower than the java.util.Arrays.sort-method on large arrays with integers drawn from a small set" in {
    val N = 5000000
    val nofTests = 10
    val M = 21
    val values = IndexedSeq.tabulate[Int](M)(j => rand.nextInt(Int.MaxValue))
    val (sortAvg, radixAvg) = compareSortAndRadixFew(nofTests, N, values)
    println(s"Averages of $nofTests arrays with $N integers from a small set:")
    println(f"  java.util.Arrays.sort: $sortAvg%.3g")
    println(f"  radix sort:            $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be <= (3.0 * sortAvg)
  }

  it should s"be faster than the java.util.Arrays.sort-method on large arrays with small integers" in {
    val N = 5000000
    val nofTests = 10
    val M = 21
    val values = IndexedSeq.tabulate[Int](M)(i => i)
    val (sortAvg, radixAvg) = compareSortAndRadixFew(nofTests, N, values)
    println(s"Averages of $nofTests arrays with $N small integers:")
    println(f"  java.util.Arrays.sort: $sortAvg%.3g")
    println(f"  radix sort:            $radixAvg%.3g")
    Console.out.flush()
    radixAvg should be < (1.0 * sortAvg)
  }
}
