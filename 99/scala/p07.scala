/**
 * P07: Flatten a nested list structure
 */

package s99.lists

package object p07 {
  // double recursion!
  def flatten(ls: List[_]): List[_] = {
    def loop(ls: List[_], acc: List[_]): List[_] = ls match {
      case Nil => acc
      case x :: xs =>
        val el: List[_] = x match {
          case l: List[_] => flatten(l)
          case _ => List(x)
        }
        loop(xs, acc ++ el)
    }
    
    loop(ls, Nil)
  }
  
  // try with fold
  def flatten2(ls: List[_]): List[_] = (ls foldLeft List[Any]()) {
    case (acc, xs: List[_]) => acc ::: flatten2(xs)
    case (acc, x) => acc ::: List(x)
  }
  
  // try to use flatten (needs all elements to be Traversables to work)
  def flatten3(ls: List[_]): List[_] = ls map {
    case xs: List[_] => flatten(xs)
    case x => List(x)
  } flatten
  
  // seems the best and shortest
  def flatten4(ls: List[_]): List[_] = ls flatMap {
    case xs: List[_] => flatten(xs)
    case x => List(x)
  }
}