package o1.carsim
import o1.Pos


class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos) {

  private var currentLocation = initialLocation
  
  def location: Pos = this.currentLocation            
  
  private var currentFuel = initialFuel
  
 
  def fuel(toBeAdded: Double) = {
    val emptyspace = this.tankSize - this.currentFuel
    val fuelatfirst = this.currentFuel
    this.currentFuel = this.currentFuel + 
      (if (toBeAdded <= emptyspace) {
        toBeAdded
      } else {
        emptyspace
      })
    val leftoverfuel = if (toBeAdded <= emptyspace) 0 else (fuelatfirst + toBeAdded - tankSize)
    toBeAdded - leftoverfuel
                
  }
   
  def fuel(): Double = {
    val aluksi = this.currentFuel
    this.currentFuel = this.tankSize
    this.tankSize - aluksi
  }
  
  def fuelRatio: Double = 100 * this.currentFuel / this.tankSize   
  
  private var driven = 0.0
    
  def metersDriven: Double = driven
     
  def fuelRange: Double = this.currentFuel * 100 * 1000 / this.fuelConsumption                                          
  
  def drive(destination: Pos, metersToDestination: Double): Unit = { 
    if (metersToDestination > 0) {
      val distance = math.min(fuelRange,metersToDestination)
      driven += distance
      currentFuel -= fuelConsumption * distance / 100000.0
      val rangeratio = distance / metersToDestination
      this.currentLocation += (destination - currentLocation) * rangeratio      
    }
  }
}

