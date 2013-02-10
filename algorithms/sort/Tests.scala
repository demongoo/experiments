package algo.sort

import algo.utils._
import io.Source

object Tests {
  def quicksort[T : ClassManifest : Ordering](n: Int, gen: =>T) {
    val arr = Array.fill(n)(gen)
    printArray(arr)
    new QuickSort(arr) sort()
    printArray(arr)
  }

  def printArray[T](seq: Array[T]) {
    seq.take(100) foreach { x => print(x); print(" ") }
    if (seq.length > 100) println(" ...") else println()
  }
}