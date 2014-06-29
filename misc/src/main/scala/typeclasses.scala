/**
 * Exploring the idea: http://www.cakesolutions.net/teamblogs/2013/12/16/typeclases-in-scala-haskell
 */
package object typeclasses {
  trait Monoid[A] {
    def mempty: A
    def mappend(a1: A, a2: A): A
  }

  implicit object IntMonoid extends Monoid[Int] {
    override def mempty = 0
    override def mappend(a1: Int, a2: Int) = a1 + a2
  }

  implicit object StringMonoid extends Monoid[String] {
    override def mempty = ""
    override def mappend(a1: String, a2: String) = a1 + a2
  }

  class ListMonoid[T] extends Monoid[List[T]] {
    override def mempty: List[T] = List.empty
    override def mappend(a1: List[T], a2: List[T]): List[T] = a1 ::: a2
  }

  def sigma[A](a: Int, b: Int, inc: Int => Int, comp: Int => A)(implicit m: Monoid[A]): A =
    if (a > b)
      m.mempty
    else
      m.mappend(comp(a), sigma(inc(a), b, inc, comp))


  trait Group[A] extends Monoid[A] {
    def inverse(a: A): A
  }
}