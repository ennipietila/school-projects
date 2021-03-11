package object change {
  def count(amount: Int, denominations: Set[Int]): Long = {
    require(amount > 0)
    require(denominations.forall(d => d > 0))
    val arr = new Array[Long](amount + 1)
    var x = 0
    while(x < amount + 1) {
      arr(x) = 0
      x += 1
    }
    arr(0) = 1
    
    val d = denominations.toArray
    for(i <- d) {
      for(n <- i until arr.length) {
        arr(n) += arr(n - i)
      }
    }
    
    arr.last
    
  }
}
