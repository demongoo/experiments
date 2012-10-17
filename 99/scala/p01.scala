/**
 * P01: Find the last element of a list
 */

package s99.lists

package object p01 {
  def last[T](list: List[T]): T = list match {
    case Nil => throw new Exception("last of empty list")
    case x :: Nil => x
    case _ :: xs => last(xs)
  }
}