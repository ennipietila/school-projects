/* Author: Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package object quickSelect {
  // (Pseudo)random number generator with a fixed seed so that
  // error situations can be reproduced easier
  val rand = new scala.util.Random(21)

  /**
   * Find the k:th smallest (0 for the smallest) element in
   * the integer sequence seq by using the quickselect algorithm.
   */
  def swap(a: Array[Int], i: Int, j: Int): Unit = {
    val t = a(i); a(i) = a(j); a(j) = t
  }
  def partition(a: Array[Int], lo: Int, hi: Int): (Int, Int) = {
      val pivotindex = lo + rand.nextInt(hi - lo + 1) 
      val pivot = a(pivotindex)
      var i = lo 
      var lt = lo 
      var gt = hi 
      while (i <= gt) {
        if (a(i) < pivot) {
          swap(a, lt, i)
          lt += 1
          i += 1
        } else if (a(i) > pivot) {         
          swap(a, i, gt)   
          gt -= 1
        } else {
          i += 1      
        }
      }     
      
      (lt, gt)     
    }
  
  def find(seq: Seq[Int], k: Int): Int = {
    require(0 <= k && k < seq.length)
    // Make a working copy of the sequence as we cannot modify the argument sequence
    val a: Array[Int] = seq.toArray
    def _find(lo: Int, hi: Int): Int = {
      require(lo <= hi)
      if(lo == hi) a(lo)
      val bounds = partition(a, lo, hi)
      
      if (bounds._2 < k) {
        _find(bounds._2 + 1, hi)
      } else if (bounds._1 > k) {
        _find(lo, bounds._1 - 1)
      } else {
        a(k)
      }
    }
    _find(0, a.length - 1) 
    
    
  }
}
