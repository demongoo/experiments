/**
 * P02: Find the last but one element of a list
 */

package s99.lists

package object p02 {
  def penultimate[T](list: List[T]): T = list match {
    case Nil | List(_) => throw new NoSuchElementException("penultimate of empty or single element list")
    case x :: y :: Nil => x
    case _ :: xs => penultimate(xs)
  }
  
  def penultimate2[T](list: List[T]) = list.init.last
}