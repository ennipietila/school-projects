
package object pairSum {
  /**
   * Find whether there is an integer v1 in list l1 and an integer v2 in list l2
   * such that v1 + v2 == target.
   * If such a pair exists, return it [with "Some((v1,v2))"]; otherwise, return None.
   * This function is allowed to run in time O(l1.length * l2.length).
   */
  def hasPairSlow(l1: List[Int], l2: List[Int], target: Int): Option[(Int, Int)] = {
    var found: Option[(Int, Int)] = None
    var i = 0
    while (found.isEmpty && i < l1.length) {
      for (n <- 0 until l2.length) {
        if (l1(i) + l2(n) == target) {
          found = Some(l1(i), l2(n))
        }
      }
      i = i + 1
    }
    found
  }

  /**
   * A faster version of hasPairSlow, must run in time
   * O(l1.length*log(l1.length) + l2.length*log(l2.length)).
   * Find whether there is an integer v1 in list l1 and an integer v2 in list l2
   * such that v1 + v2 == target.
   * If such a pair exists, return it [with "Some(( v1,v2))"]; otherwise, return None.
   */
  def hasPair(l1: List[Int], l2: List[Int], target: Int): Option[(Int, Int)] = {
    var eka = l1.sorted
    var toka = l2.sorted.reverse
    while (!eka.isEmpty && !toka.isEmpty) {
      if (eka.head + toka.head == target) {
        return Some(eka.head, toka.head)
      } else if (eka.head + toka.head > target) {
        toka = toka.tail      
      } else {
        eka = eka.tail
      }             
    }
    None   
  }
}


