import classcheck._
import categories._

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
}
