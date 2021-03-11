package o1.items

// TODO: complete as instructed in Chapter 7.3.  

class Container(name: String) extends Item(name) {
  
  private var contents = 0
  
  def addContent(newContent: Item): Unit = {
    contents = contents + 1
  }
  
  override def toString = this.name + " containing " + this.contents + " item(s)"
  
  
}