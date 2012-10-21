/**
 * P08: Eliminate consecutive duplicates of list elements
 */

package s99.lists

package object p08 {
  // recursion with dropWhile
  def compress[T](ls: List[T]): List[T] = {
    def loop(ls: List[T], acc: List[T]): List[T] = ls match {
      case Nil => acc
      case x :: xs => loop(xs dropWhile { _ == x }, x :: acc)
    }
    
    loop(ls, Nil) reverse
  }
  
  // with fold
  def compress2[T](ls: List[T]): List[T] = (ls foldLeft List[T]()) {
    case (acc, i) if acc.isEmpty || acc.head != i => i :: acc
    case (acc, _) => acc
  } reverse
  
  // with fold right
  def compress3[T](ls: List[T]): List[T] = (ls foldRight List[T]()) {
    case (i, acc) if acc.isEmpty || acc.head != i => i :: acc
    case (_, acc) => acc
  }
}