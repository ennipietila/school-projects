/* Authors: Markus Arlander and Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package object rabinKarp {
  /**
   * A reference implementation of the naive substring search algorithm.
   * Returns the starting index of the first occurrence of the pattern
   * in the text, or -1 if the pattern does not occur in the text.
   * Works in time O(nm), where n is the lenght of the text string and
   * m is the lenght of the pattern string.
   */
  def findSubstringNaive(text: String, pattern: String): Int = {
    val n = text.size
    val m = pattern.size
    val end = n - m
    var i = 0
    while(i <= end) {
      var j = i
      var k = 0
      while(k < m && text(j) == pattern(k)) {
        j += 1
        k += 1
      }
      if(k == m)
        return i
      i += 1
    }
    -1
  }

  /**
   * Substring search with the Rabin-Karp algorithm.
   * Returns the starting index of the first occurrence of the pattern
   * in the text, or -1 if the pattern does not occur in the text.
   * Works in expected time O(n+m), where n is the lenght of the text string and
   * m is the lenght of the pattern string.
   */
  def findSubstring(text: String, pattern: String): Int = {
    val n = text.size
    val m = pattern.size
    if(m > n)
      return -1
    val end = n - m
    var patternHash = 0
    var stringHash = 0
    var k = 1
    var joku = k
    var i = 1
    while(i <= m) {
      patternHash += (pattern(m - i).toInt * k)   
      stringHash += (text(m - i).toInt * k)
      joku = k
      k = k * 101
      i += 1
    }
    println("sh: " + stringHash)
    println("ph: " + patternHash)
    println("k: " + k)
    println("joku: " + joku)
    i = 0
    while(i <= end) {
      if(patternHash == stringHash) {
        if(pattern == text.substring(i, i + m)) {
          return i
        }
      } 
       if(i < end) {     
       
         stringHash -= (text(i).toInt * joku)
         stringHash = stringHash * 101
         stringHash += text(i + m).toInt
       }
      i += 1
    }
    println("vika sh: " + stringHash)
    println("jälkimmäinen")
    -1
  }                                                                                        
}
