/* Author: Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package allAnagrams
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

/**
 * A simple class for a tool that effciently finds anagrams
 * included in the given dictionary.
 * The initialization phase can take small amount of time but
 * the single queries with the 'find' method should be very fast.
 */
class AnagramFinder(val dictionary: Seq[String]) {
  // Because/if your solution needs initialization code or data structures
  // (a scala.collection.mutable.HashMap perhaps), insert them here
  val joku = new HashMap[String, ArrayBuffer[String]]() 
  def add(s: String) = {
    val sorted = s.sorted
    if(joku.contains(sorted)) {
      joku(sorted) = joku(sorted) ++ ArrayBuffer(s) 
    } else {
      joku += (sorted -> ArrayBuffer(s))
    }
  }
  dictionary.foreach(add(_))
  
  /** Find all the anagrams of the the word in the dictionary */
  def find(word: String): Set[String] = {
    joku.apply(word.sorted).toSet
  }
}
