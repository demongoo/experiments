/**
 * Exploring: http://fsharpforfunandprofit.com/posts/monoids-without-tears/#series-toc
 */
package object monoid {
  /**
   * Monoid is an algebraic structure:
   *   - 1: closure: for some type T, and combining operation o: T o T -> T:
   *   - 2: associativity: (a o b) o c = a o (b o c)
   *   - 3: identity: some 0 that a o 0 = a and 0 o a = a
   *
   *   ex. addition over ints: T = int, o = +, 0 = 0
   *       multiplication over ints: T = int, o = *, 0 = 1
   *       list concat: T = list[_], o = :::, 0 = []
   *
   * Semigroup is structure that satisfies only 1 & 2, e.g. addition over positive ints
   *
   * So...
   */

  trait SemiGroup[A] {
    /** associative closed operation */
    def op(a: A, b: A): A
  }

  trait Monoid[A] extends SemiGroup[A] {
    /** Semigroup + identity (zero) value */
    def zero: A
  }

  trait Group[A] extends Monoid[A] {
    /** Monoid + inverse */
    def inverse(a: A): A
  }


  case class OrderLine(productCode: String, qty: Int, total: Double)

  object OrderLineMonoid extends Monoid[OrderLine] {
    def zero = OrderLine("TOTAL", 0, 0.0)
    def op(a: OrderLine, b: OrderLine) = OrderLine("TOTAL", a.qty + b.qty, a.total + b.total)
  }


}