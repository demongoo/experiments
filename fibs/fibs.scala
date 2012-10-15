package goo.math

object Fibonacci {
  /**
   * Recursive fibonacci generation
   * 
   * Polinomial time O(f^n) where f = (sqrt(5) + 1) / 2
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def recursive(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case n => recursive(n - 1) + recursive(n - 2)
  }
  
  /**
   * Looping fibonacci generation
   * 
   * Polinomial time O(f^n) ?
   * 
   * @param n Number
   * @return nth fibonacci number
   */
  def loop(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case n => {
      var (f1, f2, i) = (0, 1, 2)
      while (i <= n) {
        val f = f1 + f2
        f1 = f2
        f2 = f
        i += 1
      }
      f2
    }
  }
}