/* Author: Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */
import scala.collection.mutable.HashSet

package object shortestSubstringMissing {
  /**
   * A helper iterator class that enumerates all the strings of certain length
   * over the given alphabet.
   */
  class StringIter(length: Int, alphabet: Set[Char]) extends Iterator[String] {
    require(length > 0)
    require(alphabet.nonEmpty)
    require(length < 63.0/(math.log(2) * math.log(alphabet.size)))
    private val alpha = alphabet.toArray.sorted
    private var current: Long = 0L
    private val end = (0 until length).foldLeft(1L)({case (prev,i) => prev*alpha.length})
    private var tmp = new Array[Char](length)
    def hasNext = (current != end)
    def next(): String = {
      var v = current
      for(i <- 0 until length) {
        val c = alpha((v % alpha.length).toInt)
        v = v / alpha.length
        tmp(length-i-1) = c
      }
      current += 1
      tmp.mkString("")
    }
  }

  /**
   * Find a shortest string
   * - whose characters are from the alphabet and
   * - that is not a substring of the string str.
   * A slow method given here just for reference.
   */
  def findOneSlow(str: String, alphabet: Set[Char]): String = {
    require(alphabet.nonEmpty)
    require(str.forall(c => alphabet contains c))
    var length = 1
    while(true) {
      val iter = new StringIter(length, alphabet)
      while(iter.hasNext) {
        val s = iter.next()
        if(!str.contains(s))
          return s
      }
      length += 1
    }
    "" // Just for type checking
  }


  /**
   * Find a shortest string
   * - whose characters are from the alphabet and
   * - that is not a substring of the string str.
   */
  def findOne(str: String, alphabet: Set[Char]): String = {
    require(alphabet.nonEmpty)
    require(str.forall(c => alphabet contains c))
    val n = str.length
    val m = alphabet.size
    var l = 1
    
    while(l < n) {
     
      val limit = scala.math.pow(m, l).toInt
      val setti = new HashSet[String]()
      
      var i = 0
      while(setti.size < limit && i + l <= n) {
        val s = str.substring(i, i + l)
        if (!setti.contains(s)) {
          setti += s
        }
        i += 1
      }
      
      val iter = new StringIter(l, alphabet)
      while(iter.hasNext) {
       val s = iter.next()
       if(!setti.contains(s))
         return s
      }
      
      l += 1
    }
    
      
    ""
      
    }
   
}
