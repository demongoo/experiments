import classcheck._
import categories._
import typeclasses._

object Main extends App {
  def classChecking(): Unit = {
    $[scala.collection.immutable.HashMap[_, _]] >?! $[scala.collection.Map[_, _]]
    $[scala.collection.immutable.ListMap[_, _]] >?! $[scala.collection.Map[_, _]]
    $[Number] >?! $[Any]
    $[Number] >?! $[Long]
    $[Int] >?! $[Number]
    $[Double] >?! $[Number]

    println(implicitly[Int => Number])
    println(implicitly[Double => Number])
  }

  classChecking()

  def categoryTheory(): Unit = {
    val a = (_: String).length
    val b = (_: Int) * 2
    val c = (_: Int).toString()

    println((b o a)("WTF?"))
    // associativity! :)
    println(((c o b) o a)("WTF") == (c o (b o a))("WTF"))
    // identity
    println((ℑ[Int] o ℑ[Int])(1))

    println(ListFunctor.fmap((_: String).length)(List("one", "two", "three")))

    import Functor._
    val f: String => String = _ * 2
    println(fmap(List("one", "two", "three"))(f))
    println(fmap(Option("one"))(f))
    println(fmap(() => "one")(f).apply())
  }

  categoryTheory()

  import scala.util.Try
  type |->[A, B] = PartialFunction[A, B]
  val b: Int |-> String = { case 1 => "Hello" }
  println(Try(b(1)), Try(b(2)))

  def typeClasses(): Unit = {
    println(sigma(0, 10, _ + 1, identity[Int]))
    println(sigma(0, 10, _ + 1, _.toString()))

    implicit def listMonoid[T] = new ListMonoid[T]
    println(sigma(0, 10, _ + 1, (_: Int) :: Nil))
    println(sigma(0, 10, _ + 1, i => (i.toString + ":") :: Nil))

    implicit val funMonoid = new Monoid[Int => Int] {
      override def mempty: (Int) => Int = identity[Int]
      override def mappend(a1: (Int) => Int, a2: (Int) => Int): (Int) => Int = a1 compose a2
    }

    val fm = sigma(0, 10, _ + 1, i => (j: Int) => j + i)
    println(fm(0), fm(5))
  }

  typeClasses()
}
