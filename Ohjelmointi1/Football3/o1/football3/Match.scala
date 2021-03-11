package o1.football3

import scala.collection.mutable.Buffer


class Match(val home: Club, val away: Club) {

  private val homeScorers = Buffer[Player]()    // container: goalscorers of the home team are added here
  private val awayScorers = Buffer[Player]()    // container: goalscorers of the away team are added here


  
  
  def addGoal(scorer: Player): Unit = {
    if (scorer.employer == this.home) {
      this.homeScorers += scorer
    } else {
      this.awayScorers += scorer
    }
  }
  
  def awayGoals = this.awayScorers.size
  
  def homeGoals = this.homeScorers.size
  
  def goalDifference = this.homeGoals - this.awayGoals
  
  def hasScorer(possibleScorer: Player) = {
    val allScorers = this.homeScorers ++ this.awayScorers
    allScorers.contains(possibleScorer)   
  }
    
  def isAwayWin = this.awayGoals > this.homeGoals
  
  def isHomeWin = this.awayGoals < this.homeGoals
  
  def isGoalless = this.awayGoals + this.homeGoals == 0
  
  def allScorers = (this.homeScorers ++ this.awayScorers).toVector
  
  def totalGoals = this.homeGoals + this.awayGoals
    
  def location = this.home.stadium

  def isHigherScoringThan(anotherMatch: Match) = this.totalGoals > anotherMatch.totalGoals
  
  def isTied = this.homeGoals == this.awayGoals
  
  override def toString = this.home.name + " vs. " + this.away.name + " at " + this.location + ": " +
    (if (this.isGoalless) {
      "tied at nil-nil" 
    } else if (this.isHomeWin) {
      this.homeGoals + "-" + this.awayGoals + " to " + this.home.name
    } else if (this.isAwayWin) {
      this.awayGoals + "-" + this.homeGoals + " to " + this.away.name
    } else 
      "tied at " + this.homeGoals + "-all")
      
   def winningScorer: Option[Player] = 
     if (this.isTied) {
       None
     } else if (this.isGoalless) {
       None
     } else if (this.isHomeWin) {
       Some(homeScorers(awayScorers.size))
     } else {
       Some(awayScorers(homeScorers.size))
     }
          
    def winner: Option[Club] =
      if (this.isHomeWin) {
        Some(this.home)
      } else if (this.isAwayWin) {
        Some(this.away)
      } else {
        None
      }
    
    def winnerName = { 
      winner match {
        case None => "no winner"
        case Some(voittaja) => voittaja.name
      }
    }  
          
  
}
