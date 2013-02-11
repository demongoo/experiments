package gt.coalitions

import collection.mutable

/**
 * Actually this is not from algorithms, but from game theory
 */
class Shapley(N: Int) {
  private val values: mutable.Map[Set[Int], Double] = mutable.Map(Set.empty -> 0.0)

  private def setValue(value: Double)(players: Int*) {
    values(players.toSet) = value
  }

  object v {
    def update(p1: Int, value: Double) { setValue(value)(p1) }
    def update(p1: Int, p2: Int, value: Double) { setValue(value)(p1, p2) }
    def update(p1: Int, p2: Int, p3: Int, value: Double) { setValue(value)(p1, p2, p3) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, value: Double) { setValue(value)(p1, p2, p3, p4) }
    def update(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, value: Double) { setValue(value)(p1, p2, p3, p4, p5) }

    def apply(p: Int*) = values(p.toSet)
    def apply(p: Set[Int]) = values(p)
  }

  def phis(): Seq[Double] = for (i <- 1 to N) yield phi(i)

  def phi(p: Int): Double = {
    class Ops(n: Int) {
      private def fact(x: Int): Int = if (x <= 1) 1 else x * fact(x - 1)
      def ! = fact(n)
    }

    implicit def int2ops(v: Int) = new Ops(v)

    // all subsets of 1..N without p
    val sums = (for (s <- ((1 to N).toSet - p).subsets) yield {
      (s.size!) * ((N - s.size - 1) !) * (v(s + p) - v(s))
    }).foldLeft(0.0) { _ + _ }

    sums / (N!)
  }

  val ϕ: Int => Double = phi
  val ϕόλα: () => Seq[Double] = phis
}
