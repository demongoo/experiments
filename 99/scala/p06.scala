/**
 * P06: Find out whether a list is a palindrome
 */

package s99.lists

package object p06 {
  // eh ?
  def isPalindrome(ls: List[_]) = ls == ls.reverse
  
  // recursive, but bad
  def isPalindrome2[T](ls: List[T]): Boolean = ls match {
    case Nil | _ :: Nil => true
    case _ => if (ls.head == ls.last) isPalindrome(ls.tail.init) else false
  }
  
  // moar
  def isPalindrome3[T](ls: List[T]) = ls endsWith ls.take(ls.length / 2).reverse 
}