
package object sorting {
  // Returns a sorted copy of the array d
  def radixSortDirectImmutable(d: Array[Int]): Array[Int] = {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    var copy = Array.ofDim[Int](n)
    var counter = Array.ofDim[Int](m)
    for (i <- 0 until m) {
      counter(i) = d.count(_ == i)
    }
    for (i <- 0 until m) {
      var start = counter.take(i).foldLeft(0)(_ + _)
      for (n <- start until start + counter(i)) {
        copy(n) = i
      }
    }
    copy   
  }
  
  // Sorts the array d
  def radixSortDirectMutable(d: Array[Int]) {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    var counter = Array.ofDim[Int](m)
    for (i <- 0 until m) {
      counter(i) = d.count(_ == i)
    }
    for (i <- 0 until m) {
      var start = counter.take(i).foldLeft(0)(_ + _)
      for (n <- start until start + counter(i)) {
        d(n) = i
      }
    }
       
  }

  // Returns a permutation that is a ranking of d
  def radixSortIndirect(d: Array[Int]): Array[Int] = {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    var copy = Array.ofDim[Int](n)
    var counter = Array.ofDim[Int](m)
    for (i <- 0 until m) {
      counter(i) = d.count(_ == i)
    }
    var indexes = counter.scan(0)(_ + _)
    for (i <- 0 until n) {
      copy(indexes(d(i))) = i
      indexes(d(i)) += 1
    }
    copy
  }
  
  // Returns a permutation that is a stable ranking of d
  def radixSortIndirectStable(d: Array[Int]): Array[Int] = {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    var copy = Array.ofDim[Int](n)
    var counter = Array.ofDim[Int](m)
    for (i <- 0 until m) {
      counter(i) = d.count(_ == i)
    }
    var indexes = counter.scan(0)(_ + _)
    for (i <- 0 until n) {
      copy(indexes(d(i))) = i
      indexes(d(i)) += 1
    }
    copy
  }
  
  // Returns a permutation that unranks d 
  def radixSortUnrankPerm(d: Array[Int]): Array[Int] = {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    var r = radixSortIndirectStable(d)
    val u = new Array[Int](r.length)
    (0 until u.length).foreach(j => u(r(j)) = j)
    u
  }
  
  // Returns a cell unrank array for d
  def radixSortCellUnrankArray(d: Array[Int]): Array[Int] = {
    val n = d.length
    val m = d.foldLeft(0)(_ max _)+1
    require(d.forall(_ >= 0) && m <= n)
    val r = radixSortIndirectStable(d)
    val c = new Array[Int](d.length)
    var min = -1
    var ii = -1
    for(j <- 0 until d.length) {
      val i = r(j)
      if(j == 0 || d(ii) < d(i)) {
        min = j
      }
      c(i) = min
      ii = i
    }
    c
  }
}

