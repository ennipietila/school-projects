package object knapsack {
  /**
   * 
   * A recursive backtracking search algorithm that takes exponential time in
   * the number of items.
   * Given for reference and comparison purposes.
   */
  def solveRecursive(maxWeight: Int, items: Seq[Item]): (Int, Seq[Item]) = {
    // Check that all weights and values are positive
    items.foreach({case Item(weight, value) => {
      require(weight > 0)
      require(value > 0)
    }})
    /*
     * An inner function for performing the actual search
     */
    def search(currentMaxW: Int, remainingItems: List[Item]): (Int, List[Item]) = {
      if(remainingItems.isEmpty) (0, Nil)
      else {
        val item = remainingItems.head
        // Get the best value when the item is included
        val included =
          if(item.weight > currentMaxW) (0, Nil)
          else {
            val (subValue, subSol) = search(currentMaxW - item.weight, remainingItems.tail)
            (subValue + item.value, item :: subSol)
          }
        // Get the best value when the item is not included
        val excluded = search(currentMaxW, remainingItems.tail)
        if(included._1 > excluded._1) included else excluded
      }
    }
    // Do the actual recursive seach for the solution
    // A heuristic: choose heavy objects first
    search(maxWeight, items.sortBy(_.weight).reverse.toList)
  }

  /**
   * Given a maximum weight and a list of objects as (weight, value) pairs,
   * find the highest-value subset of the items that weighs at most maxWeight.
   * Uses dynamic programming and should be much better than the recursive
   * search approach for instances with many items but
   * relatively small maxWeight.
   */
  def solveDynProg(maxWeight: Int, items: Seq[Item]): (Int, Seq[Item]) = {
    // Check that all weights and values are positive
    items.foreach({case Item(weight, value) => {
      require(weight > 0)
      require(value > 0)
    }})
    var _items = items.toArray
    var n = items.length
    var m = Array.tabulate[Int](n + 1, maxWeight + 1)((i, j) => 0)
    
    for(i <- 1 to n) {
      for(j <- 1 to maxWeight) {     
        if(_items(i - 1).weight > j) {
          m(i)(j) = m(i - 1)(j)
        } else {
          m(i)(j) = scala.math.max(m(i - 1)(j), m(i - 1)(j - _items(i - 1).weight) + _items(i - 1).value)
        }
      }
    }
    var res = Seq[Item]()
    var i = n
    var j = maxWeight
    while (i > 0 && j > 0) {
      if(m(i)(j) > m(i - 1)(j)) {
        res = res :+ _items(i - 1)
        i -= 1
        j = j - _items(i).weight
      } else {
        i -= 1
      }
    }
    
    (m(n)(maxWeight), res)
  }
}
