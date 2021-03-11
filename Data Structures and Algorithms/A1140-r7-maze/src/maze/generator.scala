/* Author: Tommi Junttila, Aalto University.
 * Only for the use on the Aalto course CS-A1140.
 * Redistribution not allowed.
 */

package maze

object generator {
  import Maze.Direction._

  /**
   * Generate a (pseudo-random) maze of given dimensions.
   * The maze must not have any cycles and all the cells must be reachable
   * from the entry cell at (0,0).
   */
  def generate(rows: Int, columns: Int, seed: Int = System.nanoTime.toInt): Maze = {
    val rand = new scala.util.Random(seed)
    val maze = new Maze(rows, columns)
    
   // val seen = Array.tabulate[Boolean](rows, columns)((i, j) => false)
    val stack = scala.collection.mutable.Stack[((Int, Int), Direction)]()
    var visited = Array[(Int, Int)]()
    
    var current = ((0, 0), Down)
    visited = visited :+ current._1
    //seen(0)(0) = true
    println(visited.size)
    while(visited.size < rows * columns) {
      val n = neighbors(current._1._1, current._1._2)
      if(!n.isEmpty) {
        val chosen = n(rand.nextInt(n.size))
        
        if(n.size > 1) stack.push(current)
        
        maze.breakWall(chosen._1._1, chosen._1._2, chosen._2)
        current = chosen
        visited = visited :+ chosen._1
      } else if(!stack.isEmpty) {
        var joku = stack.pop
        
        while(!stack.isEmpty && neighbors(joku._1._1, joku._1._2).isEmpty) {
          joku = stack.pop
        }        
        current = joku
      }
    }
    
    def neighbors(r: Int, c: Int) = {
      var res = Array[((Int, Int), Direction)]()
      if(r - 1 >= 0) res = res :+ ((r - 1, c), Up)    
      if(c - 1 >= 0) res = res :+ ((r, c - 1), Right)
      if(r + 1 < rows) res = res :+ ((r + 1, c), Down)     
      if(c + 1 < columns) res = res :+ ((r, c + 1), Left)  
      res = res.filter(x => !visited.contains(x._1))
      println(res.mkString(", "))
      res
    }
    
    maze
  }
}
