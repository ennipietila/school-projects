package o1.football2

import scala.collection.mutable.Buffer


/** The class `Match` represents match results in a football match statistics program. 
  * A match is played between teams from two clubs: a home club and an away club. 
  * Goals scored by players of either team can be added to the match object with the 
  * method `addGoal`.
  *
  * The class is expected to be used so that a match object with no goals is initially 
  * created as a real-life match starts. Goals are added incrementally as the match 
  * progresses. (A match object has mutable state.)
  *
  * @param home  the club whose team plays at home in the match
  * @param away  the club whose team plays away in the match */
class Match(val home: Club, val away: Club) {

  private val homeScorers = Buffer[Player]()    // container: goalscorers of the home team are added here
  private val awayScorers = Buffer[Player]()    // container: goalscorers of the away team are added here


  def winnerName = { 
    if (this.goalDifference < 0)
      this.away.name
    else if (this.goalDifference > 0)
      this.home.name
    else  
      "no winner" 
  }
  
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
      
   def winningScorerName = 
     if (this.isTied) {
       "no winning goal"
     } else if (this.isGoalless) {
       "no winning goal"
     } else if (this.isHomeWin) {
       homeScorers(awayScorers.size).name
     } else {
       awayScorers(homeScorers.size).name
     }
       
}


  
  
