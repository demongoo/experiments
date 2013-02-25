package algo
import utils._

object Tests extends App {
  // test case for union-finds
  /*
  unionfind.Tests.random(100000, 100000)
  unionfind.Tests.random(100000, 200000)
  unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))
  */

  sort.Tests.random(50000)
  import sort._
  val ar = Array(72, 62, 74, 61, 65, 97, 42, 28, 70, 18)
  Sort.print(ar)

  {
    val cp = ar.clone()
    new BottomUpMergeSort(cp).sort()
    Sort.print(cp)
  }
}