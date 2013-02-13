package algo
import utils._

object Tests extends App {
  // test case for union-finds
  unionfind.Tests.random(100000, 100000)
  unionfind.Tests.random(100000, 200000)
  unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))


  def qsortApprox(n: Int) = 2 * n * math.log(n)

  val α = 63.781867552630096
  val N = 23000000
  val st = (α * qsortApprox(N)).toLong // suggested time
  println("Suggested time: " + st.ms)
  val t = sort.Tests.quicksort(N, util.Random.nextInt()) // actual time
  println("Suggested time: " + st.ms + " | Experiment time: " + t.ms)
  println(t / qsortApprox(N))
}