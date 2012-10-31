package goo.math

import scala.math._

object Fibonacci {
  /**
   * Recursive fibonacci generation
   * 
   * Polinomial time O(f^n) where f = (sqrt(5) + 1) / 2
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def recursive(n: Int): Long = n match {
    case 0 => 0
    case 1 => 1
    case n => recursive(n - 1) + recursive(n - 2)
  }
  
  /**
   * Looping fibonacci generation
   * 
   * Time O(n) ? should be faster
   * than recursive and spares stack
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def loop(n: Int): Long = n match {
    case 0 => 0
    case 1 => 1
    case n => {
      var f1: Long = 0; var f2: Long = 1; var i = 2
      while (i <= n) {
        val f: Long = f1 + f2
        f1 = f2
        f2 = f
        i += 1
      }
      f2
    }
  }
  
  lazy val sqrt5 = sqrt(5)
  lazy val phi: Double = (1 + sqrt5) / 2
  lazy val psi: Double = (1 - sqrt5) / 2
  
  /**
   * Rounding fibonacci generation
   * 
   * O(1)
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def rounding(n: Int): Long = round(pow(phi, n) / sqrt5).toLong
  
  /**
   * Binet fibonacci generation
   * 
   * O(1)
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def binet(n: Int): Long = ((pow(phi, n) - pow(psi, n)) / sqrt5).toLong
  
  /**
   * Stream, mindblowing...
   */
   lazy val stream: Stream[Long] = 0 #:: 1 #:: stream.zip(stream.tail).map { n => n._1 + n._2 } 
}