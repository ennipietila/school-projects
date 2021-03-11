
package subsetFinder

import scala.collection.immutable.Set

object Finder {
  /**
   * Find one "important" element in the domain of the tester.
   * If the domain contains no important elements, return None.
   * Use binary search to implement this.
   * Sets can be split with splitAt method (see http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Set).
   * Must make at most ceil(log(tester.domain)*2)+1 calls to tester.contains.
   */
  def findOne[T](tester: Tester[T]): Option[T] = {
    var setti = tester.domain
    def find(s: Set[T]): Option[T] = {
      var splitted = s.splitAt(s.size / 2)
      if (tester.contains(s)) {
        if (s.size == 1) {
          Some(s.head)
        } else if (tester.contains(splitted._1)) {
          find(splitted._1)
        } else {
          find(splitted._2)
        }
      } else {
        None
      }
    }
    find(setti)
  }
 
  /**
   * Find all the k "important" elements in the domain of the tester
   * (the value k is not known in advance!).
   * If the domain contains no important elements, return an empty set.
   * Use binary search to implement this.
   * Must make at most k*ceil(log(tester.domain)*2)+1 calls to tester.contains.
   */
  def findAll[T](tester: Tester[T]): Set[T] = {
    var setti = tester.domain
    var res = Set[T]()
    def find(s: Set[T]): Set[T] = {
      var splitted = s.splitAt(s.size / 2)
        if (s.size == 1) {
          res += s.head
        } else {
          val cmp = tester.contains(splitted._1) compare tester.contains(splitted._2)
          if (cmp == 0) {
            find(splitted._1)
            find(splitted._2)
          } else if (cmp == 1) {
            find(splitted._1)
          } else {
            find(splitted._2)
          }                    
        }       
      
      res
      
    }
    if (tester.contains(setti)) find(setti) else res
  }
}

