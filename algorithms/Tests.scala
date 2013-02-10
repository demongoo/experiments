package algo
import utils._

object Tests extends App {
  // test case for union-finds
  //unionfind.Tests.random(100000, 100000)
  //unionfind.Tests.random(100000, 200000)
  //unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))

  println((∮ { sort.Tests.quicksort(5000000, util.Random.nextDouble()) })._1.ms)
}