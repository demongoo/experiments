/* runs as a script */
import goo.math.Fibonacci._

object FiboTest extends App {
  def max = try { args(0).toInt } catch { case _ => 40 }
  
  def test(from: Int, till: Int)(f: Int => Long): Long = {
    def measure[T](block: => T): Long = {
      val start = System.nanoTime
      try { block }
      System.nanoTime - start
    }
    
    measure { for (i <- from to till) f(i) }
  }
  
  def fmt(ns: Long) = "%.2f".format((ns / 1000).toDouble) + " µs"
  
  println("Loop 0.." + max + ": " + fmt(test(0, max)(loop)))
  println("Rounding 0.." + max + ": " + fmt(test(0, max)(rounding)))
  println("Binet 0.." + max + ": " + fmt(test(0, max)(binet)))
  println("Recursive 0.." + math.min(max, 40) + ": " + fmt(test(0, math.min(max, 40))(recursive)))
}

FiboTest main args