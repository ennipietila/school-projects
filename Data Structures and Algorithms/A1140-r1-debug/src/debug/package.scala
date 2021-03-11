/* Authors: Markus Arlander and Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package object debug {
  /** 
   * Given a sorted sequence s, finds a pair that sums to the target value.
   * Returns the pair of indices as a tuple (s, b), 
   * where s is the smaller and b the bigger index.
   * If such a pair could not be found, returns None.
   */
  def findPairSlow(s: Seq[Int], target: Int): Option[(Int, Int)] = {
    val n = s.size
    val array = s
    var bot = 0
    var top = n - 1
    while(bot < top) {
      val sum = array(bot) + array(top)
      if(sum == target)
        return Some((bot, top))
      else if(sum < target)
        bot += 1
      else
        top -= 1
    }
    None
  }
  
  /**
   * Task 1:
   * Slightly modify the method "findPairSlow" above to make it faster;
   *   write your changes to "findPairFast" below (i.e., do not modify "findPairSlow").
   * The method should give exactly the same result as "findFairSlow".
   * Hint 1: The algorithm itself is already fast in the abstract level,
   *         working in linear time _provided_ that the applied data types
   *         have certain properties.
   * Hint 2: A Seq is an abstract collection type having, e.g., List and Vector
   *         as subclasses. Study what methods are used in the code and
   *         spot the problem that makes the code run in quadratic time
   *         in the worst case. What would be the optimal collection type with
   *         respect to the applied methods?
   *         For collection type performance characteristics, please see
   *         https://docs.scala-lang.org/overviews/collections/performance-characteristics.html
   * Hint 3: Run and study the unit tests.
   * Hint 4: You only need to modify one line that introduces
   *         a linear time overhead but guarantees that the whole algorithm
   *         also works in linear time.
   */
  def findPairFast(s: Seq[Int], target: Int): Option[(Int, Int)] = {
    val n = s.size
    val array = s.toArray
    var bot = 0
    var top = n - 1
    while(bot < top) {
      val sum = array(bot) + array(top)
      if(sum == target)
        return Some((bot, top))
      else if(sum < target)
        bot += 1
      else
        top -= 1
    }
    None
  }
  
  /**
   * Finds the index of target value from the given sorted array values
   * using binary search. If the value could not be found, return None.
   * https://en.wikipedia.org/wiki/Binary_search_algorithm
   * 
   * Task 2:
   * There are some mistakes in the implementation of the algorithm,
   * find and fix them so that the method works correctly.
   */
  def binarySearch(values: Array[Int], target: Int): Option[Int] = {
    val n = values.size
    var left = 0
    var right = n - 1
    while(left <= right){
      val mid = (left + right) / 2
      val value = values(mid)
      if(value == target)
        return Some(mid)
      else if(value > target)
        right = mid - 1
      else
        left = mid + 1
    }
    None
  }
  
  /**
   * Performs a simple "box blur" on a 2-dimensional array of Doubles.
   *
   * The input 2D-array is of size rows*columns and is given as
   * an 1-dimensional Array in row-major order:
   * the first elements 0,1,...,columns-1 form the first row,
   * columns,columns+1,...2*columns-1 the second row, and so on
   * (see https://en.wikipedia.org/wiki/Row-_and_column-major_order ).
   * 
   * The output is a rows*columns 2D-array in row-major order
   * obtained by calculating, for each element in a position (r,c),
   * the average of the input array elements in the positions
   * (r-dim,c-dim),...,(r+dim,c-dim),(r-dim,c-dim+1),...,(r+dim,c-dim+1),
   * (r-dim,c+dim),...,(r+dim,c+dim).
   * The values in the output array "boundaries", i.e. positions (r,c)
   * in which r-dim < 0, r+dim >= rows, c-dim < 0 or c+dim >= columns,
   * are assumed to be the same as in the input array.
   *
   * The method shall not modify the input array but
   * returns a new array containing the blurred data.
   *
   * For reference and further information about blur filters, see e.g.
   * https://en.wikipedia.org/wiki/Gaussian_blur
   * https://en.wikipedia.org/wiki/Box_blur
   *
   * Task 3:
   * Find and fix the mistakes in the given method.
   * All of the mistakes in the given method are fairly simple 
   * programming mistakes and should be easy to fix.
   * The actual algorithm idea used in the method is correct.
   * (Note: the algorithm is not the most efficient one: one could leverage
   *  the fact that the applied filter is separable, see
   *  https://en.wikipedia.org/wiki/Separable_filter
   *  However, you don't have to consider this issue in this exercise.)
   */
  def boxBlur(values: Array[Double], 
                rows: Int, 
                columns: Int, 
                dim: Int): Array[Double] = {
    require(rows * columns == values.size)
    require(dim >= 1)
    
    val averages = values.clone
    val divisor = (2*dim+1) * (2*dim+1)

    for(r <- dim until rows - dim) {
      for(c <- dim until columns - dim) {
        var sum = 0.0
        for(r2 <- (r - dim) to (r + dim)) {
          for(c2 <- (c - dim) to (c + dim)) {
            sum += values(r2*columns + c2)
          }
        }
        averages(r*columns + c) = sum / divisor
      }
    }
    averages
  }
}
