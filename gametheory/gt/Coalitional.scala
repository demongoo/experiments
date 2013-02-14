package gt.coalitional

import collection.mutable

/**
 * Base class for representing coalitional games
 *
 * Coalitional games are represented by (N, v), where
 *   N - set of players (in our case players are enumerated as 1..N)
 *   v - characteristic function: associates with each coalition S ⊆ N a real-valued payoff v(S)
 *       that the coalition’s members can distribute among themselves. We assume that v(∅) = 0
 *
 * @param N Number of players
 */
abstract class GameDefinition(val N: Int) {
  /** represents a set of players formed coalition, e.g. {1,2,5}, {2}, {} */
  type Coalition = Set[Int]

  /** payoff is a real value, though it is useful sometimes to define as function v(S) */
  type Payoff = Double
  type PayoffFunction = PartialFunction[Coalition, Payoff]

  /** full set of players */
  private[coalitional] val SN = (1 to N).toSet

  /** Map which holds concrete payoffs */
  private[coalitional] val payoffs: mutable.Map[Coalition, Payoff] = mutable.Map(Set.empty -> 0.0)

  /** List with functional payoffs, defined as v(S) */
  private[coalitional] val payoffFunctions: mutable.ListBuffer[PayoffFunction] = mutable.ListBuffer()

  /** Sets simple payoff */
  private[coalitional] def setPayoff(p: Payoff)(players: Int*) = { payoffs(players.toSet) = p; p }

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
    def update(p1: Int, value: Double) = setPayoff(value)(p1)
    def update(p1: Int, p2: Int, value: Double) = setPayoff(value)(p1, p2)
    def update(p1: Int, p2: Int, p3: Int, value: Double) = setPayoff(value)(p1, p2, p3)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5, p6)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5, p6, p7)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9)
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, p10: Int, value: Double) = setPayoff(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)

    /** all up from 10 is possible using v(Set(1,2,3,4,5.....)) = X */
    def update(c: Coalition, value: Double) = { payoffs(c) = value; value }

    /** and algorithmic payoffs, say, if set not contains 7 return 0 */
    def update(s: S.type, pf: PayoffFunction) { payoffFunctions += pf }

    /** value of payoff specified by sequence of players or set */
    def apply(p: Int*): Payoff = (payoffs orElse this(S)) (p.toSet)
    def apply(p: Coalition): Payoff = (payoffs orElse this(S)) (p)

    /** returns partial function chain of payoff functions */
    def apply(s: S.type) = (payoffFunctions foldLeft undefined) { (acc, pf) => acc orElse pf }
  }

  /** couple of frequent math operations */
  protected[coalitional] object Math {
    /** natural numbers */
    lazy val naturals: Stream[Long] = 1 #:: (naturals map { _ + 1 })

    /** factorial */
    lazy val fact: Stream[Long] = 1 #:: (fact zip naturals map { case (x, y) => x * y })

    /** cartesian product of two seqs with predicate*/
    def cartesianProductWithFilter[T](s: Seq[T], t: Seq[T])(predicate: (T, T) => Boolean): Seq[(T, T)] =
      for {
        ss <- s
        tt <- t
        if predicate(ss, tt)
      } yield (ss, tt)

    /** implicit predicate returning true always */
    implicit val True: (Any, Any) => Boolean = (_, _) => true

    /** wrapper for seq */
    class SeqOps[T](seq: Seq[T]) {
      /** cartesian product */
      def ×(that: Seq[T])(implicit predicate: (T, T) => Boolean) = cartesianProductWithFilter(seq, that)(predicate)
    }

    implicit def seq2sops[T](seq: Seq[T]) = new SeqOps(seq)

    /** comparisons for doubles */
    case class Precision(val p: Double)

    class DoubleComparisons(d: Double) {
      def ~=(d2: Double)(implicit p: Precision) = (d - d2).abs <= p.p
      def ~>=(d2: Double)(implicit p: Precision) = d > d2 || ~=(d2)
      def ~<=(d2: Double)(implicit p: Precision) = d < d2 || ~=(d2)
    }

    implicit def double2dc(d: Double) = new DoubleComparisons(d)

    implicit val precision = Precision(1e-8)
  }

  /**
   * Check if game is superadditive
   *
   * A game G = (N, v) is superadditive if for all S, T ⊂ N,
   * if S ∩ T = ∅, then v(S ∪ T) ≥ v(S) + v(T)
   */
  def isSuperadditive: Boolean = {
    import Math._
    val subsets = SN.subsets.toStream

    (subsets × subsets) {
      (x, y) => (x intersect y).isEmpty
    } forall {
      case (s, t) => v(s union t) ~>= v(s) + v(t)
    }
  }

  /**
   * Check if game is convex
   *
   * A game G = (N, v) is convex if for all S, T ⊂ N,
   * then v(S ∪ T) >=  v(S) + v(T) - v(S ∩ T)
   */
  def isConvex: Boolean = {
    import Math._
    val subsets = SN.subsets.toStream

    (subsets × subsets) forall {
      case (s, t) => v(s union t) ~>= v(s) + v(t) - v(s intersect t)
    }
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
    // iterate through all subsets of 1..N without player i
    val sums = for (s <- (SN - i).subsets) yield {
      Math.fact(s.size) * Math.fact(N - s.size - 1) * (v(s + i) - v(s))
    }

    (sums foldLeft 0.0) { _ + _ } / Math.fact(N)
  }

  /** alias */
  val ϕ = shapleyValue _
}


/**
 * Calculations related to the core
 */
trait TheCore { this: GameDefinition =>
  /**
   * Check if payoff vector is in the core
   *
   * A payoff vector x is in the core of a coalitional game (N, v)
   * iff for ∀ S ⊆ N, ∑xi >= v(S)
   */
  def isInCore(x: Seq[Payoff]): Boolean = {
    import Math._

    x.size == N &&
    (x.sum ~= v(SN)) &&
    SN.subsets.forall {
      s => (s.toList map { i => x(i - 1) }).sum ~>= v(s)
    }
  }

  /**
   * Alias with vararg
   */
  def isInCore(x: Payoff*)(implicit d: DummyImplicit): Boolean = isInCore(x)
}


/** Full featured coalitional game, but should be defined of course */
abstract class Game(N: Int) extends GameDefinition(N)
  with ShapleyValue
  with TheCore