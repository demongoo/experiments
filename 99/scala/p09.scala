/**
 * P09: Pack consecutive duplicates of list elements into sublists
 */

package s99.lists

package object p09 {
  // recursion with takeWhile & dropWhile
  def pack[T](ls: List[T]): List[List[T]] = {
    def loop(ls: List[T], acc: List[List[T]]): List[List[T]] = ls match {
      case Nil => acc
      case whole @ (x :: xs) => loop(whole dropWhile { _ == x }, (whole takeWhile { _ == x }) :: acc)
    }
    
    loop(ls, Nil) reverse
  }
  
  // recursion span
  def pack2[T](ls: List[T]): List[List[T]] = 
    if (ls.isEmpty) Nil
    else {
      val (prefix, suffix) = ls span { _ == ls.head }
      prefix :: pack2(suffix)
    }
}