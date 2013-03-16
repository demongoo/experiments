package algo

package object utils {
  /** just declares that object can print itself */
  trait Printable {
    def print()
  }

  /** block to measure time */
  def ∮[T](block: => T): (Long, T) = {
    val start = System.nanoTime
    val ret = try { block }
    (System.nanoTime - start, ret)
  }

  /** format nanotime to micro seconds */
  class TimeFormatting(ns: Long) {
    def µs = "%.2f".format(ns.toDouble / 1000) + " µs"
    def ms = "%.2f".format(ns.toDouble / 1000000) + " ms"
  }

  implicit def long2tf(i: Long) = new TimeFormatting(i)
}