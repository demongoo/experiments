/** Sort algorithms (for mutable collections) */
package algo.sort

import algo.utils._

/** just obliges class to do sorting on mutable sequence */
trait Sort[T] {
  type S
  def sort(): S
}

/** Straight Quick Sort
  * @param n Number of nodes
  */
class QuickSort[T](seq: Array[T])(implicit ord: Ordering[T]) extends Sort[T] {
  type S = Array[T]

  /* entry point */
  def sort() = {
    qsort(0, seq.length - 1)
    seq
  }

  /* actual quick sort procedure */
  protected def qsort(lo: Int, hi: Int) {
    if (hi > lo) {
      val j = partition(lo, hi)
      qsort(lo, j - 1)
      qsort(j + 1, hi)
    }
  }

  protected def partition(lo: Int, hi: Int): Int = {
    import util.control.Breaks._

    def swap(x: Int, y: Int) { val t = seq(x); seq(x) = seq(y); seq(y) = t }

    var (i, j) = (lo + 1, hi)
    breakable {
      while (true) {
        while (ord.lteq(seq(i), seq(lo))) { if (i == hi) break(); i += 1 }
        while (ord.lt(seq(lo), seq(j))) { if (j == lo) break(); j -= 1 }
        if (i >= j) break()
        swap(i, j)
      }
    }

    swap(lo, j)
    j
  }
}