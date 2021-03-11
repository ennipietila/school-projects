package o1.election

import scala.collection.mutable.Buffer

class District(val name: String, val seats: Int, val candidates: Vector[Candidate]) {
  
  override def toString = this.name + ": " + this.candidates.size + " candidates, " +
    this.seats + " seats"
    
  def printCandidates() = candidates.foreach( println(_) + "\n" )
   
  
  
  def candidatesFrom(party: String): Vector[Candidate] = {
    candidates.filter( _.party == party )    
  }
  
  def topCandidate: Candidate = {
    this.candidates.maxBy(_.votes)    
  }
  
  def totalVotes: Int = {
    candidates.map(_.votes).sum
  }  
    
  def totalVotes(party: String): Int = {
    candidates.filter(_.party == party).map(_.votes).sum
        
  }
  
  private def countVotes(candidates: Vector[Candidate]) = {
    candidates.map(_.votes).sum    
  }
  
  def candidatesByParty: Map[String, Vector[Candidate]] = {
    this.candidates.groupBy(_.party)
  }
      
  def topCandidatesByParty: Map[String, Candidate] = {
    this.candidatesByParty.mapValues(_.maxBy(_.votes))
  }
  
  def votesByParty: Map[String, Int] = {
    this.candidatesByParty.mapValues(countVotes(_))
  }
   
  def rankingsWithinParties: Map[String, Vector[Candidate]] = {
    this.candidatesByParty.mapValues(_.sortBy(-_.votes))
  }
  
  def rankingOfParties: Vector[String] = {
    this.votesByParty.toVector.sortBy(-_._2).map(_._1)
  }
  
  def distributionFigures: Map[Candidate, Double] = {
    
    def figure(candidate: Candidate) = {
      votesByParty(candidate.party).toDouble / (rankingsWithinParties(candidate.party).indexOf(candidate).toDouble + 1.0)
    }
    
    this.candidates.zip(this.candidates.map(figure(_))).toMap
  }
  
  def electedCandidates: Vector[Candidate] = {
    this.distributionFigures.toVector.sortBy(-_._2).map(_._1).take(this.seats)
  }
  
  
}