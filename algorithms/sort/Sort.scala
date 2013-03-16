/** Sort algorithms (for mutable collections) */
package algo.sort

import algo.utils._

/** just obliges class to do sorting on mutable sequence */
abstract class Sort[T: Ordering](val seq: Array[T]) {
  val ord = implicitly[Ordering[T]]
  def sort(): Array[T]

  @inline protected def swap(i: Int, j: Int) {
    val t = seq(i)
    seq(i) = seq(j)
    seq(j) = t
  }
}

/** companion to hold a couple of useful methods */
object Sort {
  /** check if sorted in ascending order */
  def isSorted[T : Ordering](array: Array[T]): Boolean = {
    array.sliding(2).foldLeft(true) {
      case (false, _) => return false
      case (true, Array(x, y)) => implicitly[Ordering[T]].lteq(x, y)
      case _ => true
    }
  }

  /** knuth shuffling */
  def shuffle[T: Ordering](array: Array[T]) {
    for {
      i <- 0 until array.length
      r = util.Random.nextInt(i + 1)
      v = array(i)
    } {
      array(i) = array(r)
      array(r) = v
    }
  }

  /** print array */
  def print[T](array: Array[T]) {
    println(array.mkString("[ ", " ", " ]"))
  }
}

/**
 * Selection sort (N^2/2, N^2/2, N^2/2) compares and N exchanges
 * @param seq Array
 */
class SelectionSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    for (i <- 0 until seq.length) {
      var min = i
      for (j <- (i + 1) until seq.length) {
        if (ord.lt(seq(j), seq(min))) min = j
      }
      swap(i, min)
    }

    seq
  }
}


/**
 * Insertion sort (N^2/2, N^2/4, N) compares and ~N^2/4 exchanges
 * @param seq Array
 */
class InsertionSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    for (i <- 0 until seq.length) {
      var j = i
      while (j > 0 && ord.lt(seq(j), seq(j - 1))) {
        swap(j, j - 1)
        j -= 1
      }
    }

    seq
  }
}


/**
 * Shell sort (?, ?, N) compares
 * @param seq Array
 */
class ShellSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    val N = seq.length
    var h = 1
    while (h < N/3) h = 3 * h + 1 // 1, 4, 13, 40, 121, 364, ...

    // h-sort the array.
    while (h >= 1) {
      for (i <- h until N) {
        var j = i
        while (j >= h && ord.lt(seq(j), seq(j - h))) {
          swap(j, j - h)
          j -= h
        }
      }

      h = h/3
    }

    seq
  }
}


/**
 * Merge sort (N lg N, N lg N, N lg N) compares, stable
 * @param seq Array
 */
class MergeSort[T : Ordering : ClassManifest](seq: Array[T]) extends Sort[T](seq) {
  protected val aux = new Array[T](seq.length)

  def sort() = {
    sort(0, seq.length - 1)
    seq
  }

  protected def sort(lo: Int, hi: Int) {
    if (hi <= lo) return
    val mid = lo + (hi - lo) / 2
    sort(lo, mid)
    sort(mid + 1, hi)
    merge(lo, mid, hi)
  }

  protected def merge(lo: Int, mid: Int, hi: Int) {
    for (k <- lo to hi) aux(k) = seq(k)

    var i = lo
    var j = mid + 1
    for (k <- lo to hi) {
      if (i > mid) { seq(k) = aux(j); j += 1 }
      else if (j > hi) { seq(k) = aux(i); i += 1 }
      else if (ord.lt(aux(j), aux(i))) { seq(k) = aux(j); j += 1 }
      else { seq(k) = aux(i); i += 1 }
    }
  }
}


/**
 * Merge sort (N lg N, N lg N, N lg N) compares, stable
 * @param seq Array
 */
class BottomUpMergeSort[T : Ordering : ClassManifest](seq: Array[T]) extends MergeSort[T](seq) {
  override def sort() = {
    val N = seq.length
    var sz = 1
    while (sz < N) {
      var lo = 0
      while (lo < N - sz) {
        merge(lo, lo + sz - 1, math.min(lo + sz + sz - 1, N - 1))
        lo += 2 * sz
      }
      sz += sz
    }

    seq
  }
}


/**
 * Quick sort (N^2/2, 2NlnN, NlgN) compares
 * @param seq Array
 */
class QuickSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    Sort.shuffle(seq)
    qsort(0, seq.length - 1)
    seq
  }

  protected def qsort(lo: Int, hi: Int) {
    if (hi > lo) {
      val j = partition(lo, hi)
      qsort(lo, j - 1)
      qsort(j + 1, hi)
    }
  }

  protected def partition(lo: Int, hi: Int): Int = {
    var i = lo
    var j = hi + 1

    do {
      while ({ i += 1; ord.lt(seq(i), seq(lo)) && i != hi }) {}
      while ({ j -= 1; ord.lt(seq(lo), seq(j)) && j != lo }) {}
      if (i < j) swap(i, j)
    } while (i < j)

    swap(lo, j)
    j
  }
}


/**
 * Three way quick-sort (Dijkstra) (N^2/2, 2NlnN, N) compares
 * @param seq Array
 */
class ThreeWayQuickSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    Sort.shuffle(seq)
    sort(0, seq.length - 1)
    seq
  }

  protected def sort(lo: Int, hi: Int) {
    if (hi > lo) {
      var lt = lo
      var gt = hi
      val v = seq(lo)
      var i = lo
      while (i <= gt) {
        val cmp = ord.compare(seq(i), v)
        if (cmp < 0) { swap(lt, i); lt += 1; i += 1 }
        else if (cmp > 0) { swap(i, gt); gt -= 1 }
        else i += 1
      }

      sort(lo, lt - 1)
      sort(gt + 1, hi)
    }
  }
}


/**
 * heap sort (2NlnN, 2NlnN, NlnN) compares
 * @param seq Array
 */
class HeapSort[T : Ordering](seq: Array[T]) extends Sort[T](seq) {
  def sort() = {
    var N = seq.length
    for (k <- N/2 to 1 by -1) sink(k, N)
    while (N > 1) {
      swap(0, N - 1)
      N -= 1
      sink(1, N)
    }
    seq
  }

  protected def sink(x: Int, N: Int) {
    var k = x
    while (2 * k <= N) {
      var j = 2 * k
      if (j < N && ord.lt(seq(j - 1), seq(j))) j += 1
      if (!ord.lt(seq(k - 1), seq(j - 1))) { return }
      swap(k - 1, j - 1)
      k = j
    }
  }
}