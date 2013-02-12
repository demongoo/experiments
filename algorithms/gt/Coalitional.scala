package gt.coalitional

import collection.mutable

/**
 * Base class for representing coalitional games
 *
 * Coalitional games are represented by (N, v), where
 *   N - set of players (in our case players are enumerated as 1..N)
 *   v - associates with each coalition S ⊆ N a real-valued payoff v(S) that the coalition’s members can
 *       distribute among themselves. We assume that v(∅) = 0
 *
 * @param N Number of players
 */
abstract class GameDefinition(val N: Int) {
  /** represents a set of players formed coalition, e.g. {1,2,5}, {2}, {} */
  type Coalition = Set[Int]

  /** payoff is a real value, though it is useful sometimes to define a function v(S) */
  type Payoff = Double
  type PayoffFunction = PartialFunction[Coalition, Payoff]

  /** Map which holds concrete payoffs */
  private[coalitional] val payoffs: mutable.Map[Coalition, Payoff] = mutable.Map(Set.empty -> 0.0)

  /** List with functional payoffs, defined as v(S) */
  private[coalitional] val payoffFunctions: mutable.ListBuffer[PayoffFunction] = mutable.ListBuffer()

  /** Sets simple payoff */
  private[coalitional] def setPayoff(p: Payoff)(players: Int*) {
    payoffs(players.toSet) = p
  }

  /** Sets payoff function */
  private[coalitional] def setPayoff(pf: PayoffFunction) {
    payoffFunctions += pf
  }

  /** just dummy to write v(S) = { ... } */
  protected case object S

  /** object as a shortcut for v, yeah, updates are ugly, but it allows to write v(1,2,3,4,6) = 5 which is great */
  protected object v {
    /** used as default partial function */
    private val undefined: PartialFunction[Coalition, Payoff] = Map.empty

    /** allows to write v(1,2,3) = 2 up to 10 players */
    def update(p1: Int, value: Double) { setPayoff(value)(p1) }
    def update(p1: Int, p2: Int, value: Double) { setPayoff(value)(p1, p2) }
    def update(p1: Int, p2: Int, p3: Int, value: Double) { setPayoff(value)(p1, p2, p3) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5, p6) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5, p6, p7) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, p10: Int, value: Double) { setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }

    /** all up from 10 is possible using v(Set(1,2,3,4,5.....)) = X */
    def update(c: Coalition, value: Double) { payoffs(c) = value }

    /** and algorithmic payoffs, say, if set not contains 7 return 0 */
    def update(s: S.type, pf: PayoffFunction) { payoffFunctions += pf }

    /** value of payoff specified by sequence of players or set */
    def apply(p: Int*) = payoffs(p.toSet)
    def apply(p: Coalition) = payoffs(p)

    /** returns partial function chain of payoff functions */
    def apply(s: S.type) = (payoffFunctions foldLeft undefined) { (acc, pf) => acc orElse pf }
  }

  /** couple of frequent math operations */
  protected[coalitional] object Math {
    /** natural numbers */
    lazy val naturals: Stream[Long] = 1 #:: (naturals map { _ + 1 })

    /** factorial */
    lazy val fact: Stream[Long] = 1 #:: (fact zip naturals map { case (x, y) => x * y })
  }
}


/**
 * Calculation of shapley value
 */
trait ShapleyValue { this: GameDefinition =>
  /**
   * Calculates Shapley Value for playes i
   * @param i Player number
   * @return Player's payoff
   */
  def shapleyValue(i: Int): Double = {
    /** gets payoff for coalition */
    def payoff(s: Coalition): Double =
      try { v(s) }
      catch { case _: Exception => v(S)(s) }

    // iterate through all subsets of 1..N without player i
    val sums = for (s <- ((1 to N).toSet - i).subsets) yield {
      Math.fact(s.size) * Math.fact(N - s.size - 1) * (payoff(s + i) - payoff(s))
    }

    (sums foldLeft 0.0) { _ + _ } / Math.fact(N)
  }

  /** alias */
  val ϕ = shapleyValue _
}


/** Full featured coalitional game */
class Game(N: Int) extends GameDefinition(N) with ShapleyValue