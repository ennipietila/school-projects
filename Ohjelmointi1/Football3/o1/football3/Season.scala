package o1.football3

import scala.collection.mutable.Buffer

class Season() {
  
  private val matches = Buffer[Match]()
  private var highestmargin: Option[Match] = None
  
  def addResult(newResult: Match): Unit = {
    matches += newResult   
    highestmargin match {
      case None =>
        this.highestmargin = Some(newResult)
      case Some(previous) =>
        if (math.abs(previous.goalDifference) > math.abs(newResult.goalDifference)) {
          this.highestmargin = Some(previous)
        } else if (math.abs(previous.goalDifference) < math.abs(newResult.goalDifference)) {
          this.highestmargin = Some(newResult)
        } else {
          this.highestmargin = Some(previous)
        }   
      }  
  }
  
  def biggestWin: Option[Match] = {
    this highestmargin match {
      case None => None
      case Some(previousmatch) => Some(previousmatch)
                          
    } 
  }
  
  def latestMatch: Option[Match] = {
    if (matches.isEmpty) {
      None
    } else {
      Some(matches(matches.size -1))
    }
  }
  
  def matchNumber(number: Int): Option[Match] = {
    if (matches.isEmpty) {
      None
    } else if (number >= matches.size) {
      None
    } else {  
      Some(matches(number))
    }
      
  }
  
  def numberOfMatches: Int = matches.size
}