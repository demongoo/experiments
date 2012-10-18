/**
 * P05: Reverse a list
 */

package s99.lists

package object p05 {
  def reverse(ls: List[_]): List[_] = ls match {
    case Nil => Nil
    case x :: xs => reverse(xs) ::: List(x)
  }
  
  // built-in
  def reverse2(ls: List[_]) = ls.reverse
  
  // another
  def reverse3[T](ls: List[T]): List[T] = (ls foldLeft List[T]()) { (acc, e) => e :: acc }
}