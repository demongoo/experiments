package algo
import utils._

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

  import gt.coalitions._

  new Shapley(2) {
    v(1) = 0
    v(2) = 2
    v(1,2) = 2

    println(ϕ(1))
    println(ϕ(2))
  }

  new Shapley(3) {
    v(1) = 0
    v(2) = 0
    v(3) = 0
    v(1,2) = 90
    v(1,3) = 80
    v(2,3) = 70
    v(1,2,3) = 120

    // 45, 40, 35
    println(ϕόλα())
  }
}