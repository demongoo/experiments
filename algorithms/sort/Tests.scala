package algo.sort

import algo.utils._
import io.Source

object Tests {
  def quicksort[T : ClassManifest : Ordering](n: Int, gen: =>T): Long = {
    val arr = Array.fill(n)(gen)
    printArray(arr)
    val (t, _) = ∮ { new QuickSort(arr) sort() }
    printArray(arr)

    t
  }

  def printArray[T](seq: Array[T]) {
    seq.take(100) foreach { x => print(x); print(" ") }
    if (seq.length > 100) println(" ...") else println()
  }
}