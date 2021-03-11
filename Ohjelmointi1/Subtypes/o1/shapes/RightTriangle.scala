package o1.shapes

class RightTriangle(val kateetti: Double, val toinenkateetti: Double) extends Shape {
  
  def area = this.kateetti * this.toinenkateetti / 2
  
  def hypotenuse = math.sqrt(this.kateetti * this.kateetti + this.toinenkateetti * this.toinenkateetti)
  
  def perimeter = this.kateetti + this.toinenkateetti + this.hypotenuse
}