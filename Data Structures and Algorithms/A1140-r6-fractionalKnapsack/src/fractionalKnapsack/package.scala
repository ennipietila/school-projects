package object fractionalKnapsack {
  /**
   * Solves fractional knapsack problem instances.
   * Input
   * - maxAmount is the maximum amount that the container can hold
   * - materials gives the available materials
   * Output is a pair with
   * - the maximum profit we can get
   * - and a sequence of pairs of form (a,m) describing how the maximum
   *   profit can be get, a pair (a,m) stating that we should include
   *   the amount a of material m
   */
  def solve(maxAmount: Double, materials: Seq[Material]): (Double, Seq[(Double, Material)]) = {
    require(maxAmount >= 0.0)
    val sorted = materials.sortBy(_.valuePerUnit).reverse
    
    var result = Vector[(Double, Material)]()
    var profit = 0.0
    var amountleft = maxAmount
    
    for(n <- sorted) {
      if(n.amount <= amountleft){
        amountleft -= n.amount
        result = result :+ (n.amount, n)
        profit += n.amount * n.valuePerUnit
      } else {
        result = result :+ (amountleft, n)
        profit += amountleft * n.valuePerUnit
        amountleft = 0
        
      }
    }
    
    (profit, result)
  }
}
