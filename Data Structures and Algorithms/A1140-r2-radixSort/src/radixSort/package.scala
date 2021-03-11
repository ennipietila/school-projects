/* Author: Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package object radixSort {
  /**
   * Least-significant-digit-first radix sort for integer arrays.
   * Sorts the argument array in ascending order.
   * Assumes that all the integers are non-negative.
   * Interpret 'digit' as 'byte' (8 bits) here and use bit-level operations
   *  such as shifting (>> and <<) and masking (&) to extract 'digits'.
   */
  def lsdRadixSort(a: Array[Int]): Unit = {
    val N = a.length
    if(N <= 1) return 
    var count = new Array[Int](256)
    var joku = new Array[Int](a.length)
    var i = 0
    while(i < 4) {
      var j = 0
      while(j < 256) {
        count(j) = 0
        j += 1
      }
      j = 0
      while(j < a.length) {
        count((a(j) >> i*8) & 0xff) = count((a(j) >> i*8) & 0xff) + 1
        j += 1
      }
      if(count(0) == a.length) return
      count(0) = count(0) - 1
      j = 1
      while(j < 256) {
        count(j) = count(j - 1) + count(j)
        j += 1
      }
      j = a.length - 1
      while(j >= 0) {
        joku(count((a(j) >> i*8) & 0xff)) = a(j)
        count((a(j) >> i*8) & 0xff)  = count((a(j) >> i*8) & 0xff) - 1
        j -= 1
      }
      j = 0
      while(j < a.length){
        a(j) = joku(j)
        j += 1
      }
        
      i += 1
    }
    
  }
}
