
package object suffixBuild {
  def suffixArray(s: String): Seq[Int] = {
    s.zipWithIndex.map(x => (s.drop(x._2), x._2)).sorted.map(_._2)
  }
}


