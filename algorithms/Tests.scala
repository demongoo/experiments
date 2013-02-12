package algo
import utils._
import gt.coalitional.Game

object Tests extends App {
  // test case for union-finds
  //unionfind.Tests.random(100000, 100000)
  //unionfind.Tests.random(100000, 200000)
  //unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))

  /*
  def qsortApprox(n: Int) = 2 * n * math.log(n)

  val α = 63.781867552630096
  val N = 23000000
  val st = (α * qsortApprox(N)).toLong // suggested time
  println("Suggested time: " + st.ms)
  val t = sort.Tests.quicksort(N, util.Random.nextInt()) // actual time
  println("Suggested time: " + st.ms + " | Experiment time: " + t.ms)
  println(t / qsortApprox(N))
  */

  import gt.coalitional._

  new Game(2) {
    v(1) = 0
    v(2) = 2
    v(1,2) = 2

    println((1 to N) map ϕ)
  }

  new Game(3) {
    v(1) = 0
    v(2) = 0
    v(3) = 0
    v(1,2) = 90
    v(1,3) = 80
    v(2,3) = 70
    v(1,2,3) = 120

    // Shapley value: 45, 40, 35
    println((1 to N) map ϕ)
  }

  new Game(3) {
    v(S) = {
      case s: Coalition if s.contains(1) && s.size >= 2 => 1
      case _ => 0
    }: PayoffFunction

    // Shapley value: 2/3, 1/6, 1/6
    println((1 to N) map ϕ)
  }

  /*
  new Game(3) {
    val c = 1
    val w1 = 2
    val w2 = 3

    v(S) = {
      case s: Coalition if (Set(c, w1) &~ s).isEmpty => 3
      case s: Coalition if (Set(c, w2) &~ s).isEmpty => 3
      case s: Coalition if (Set(c, w2, w2) &~ s).isEmpty => 4
      case _ => 0
    }: PayoffFunction

    println((1 to N) map ϕ)
  }
  */
}