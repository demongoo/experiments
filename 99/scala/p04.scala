/**
 * P04: Find the number of elements of a list
 */

package s99.lists

package object p04 {
  // not tail-recursive
  def length(ls: List[_]): Int = ls match {
    case Nil => 0
    case _ :: xs => 1 + length(xs)
  }
  
  // built-in
  def length2(ls: List[_]) = ls.length
  
  // tail-recursive
  def length3(ls: List[_]): Int = {
    def loop(xs: List[_], acc: Int): Int = xs match {
      case Nil => acc
      case _ :: xs => loop(xs, acc + 1)
    }
    
    loop(ls, 0)
  }
}