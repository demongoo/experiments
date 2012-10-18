/**
 * P03: Find the Kth element of a list
 */

package s99.lists

package object p03 {
  def nth[T](n: Int, ls: List[T]): T = ls match {
    case Nil => throw new NoSuchElementException("empty list")
    case x :: xs if n == 0 => x 
    case _ :: xs => nth(n - 1, xs)
  }
  
  def nth2[T](n: Int, ls: List[T]) = ls(n)
}