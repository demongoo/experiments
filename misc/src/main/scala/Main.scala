import classcheck._

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
}
