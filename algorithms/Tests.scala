package algo
import utils._

object Tests extends App {
  // test case for union-finds
  /*
  unionfind.Tests.random(100000, 100000)
  unionfind.Tests.random(100000, 200000)
  unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))
  */

  /*
  sort.Tests.random(1000)
  sort.Tests.random(100000)
  sort.Tests.random(100000, 50000)
  sort.Tests.random(100000, 10000)
  sort.Tests.random(100000, 100)
  sort.Tests.data((0 until 100000).toArray, "presorted")
  sort.Tests.data((0 until 100000).reverse.toArray, "reverse")
  sort.Tests.data(Array.fill(100000)(1), "just ones")

  val onetwos: Stream[Int] = {
    def loop(v: Int): Stream[Int] = v #:: loop(if (v == 1) 2 else 1)
    loop(1)
  }

  sort.Tests.data(onetwos take 100000 toArray, "1 2 1 2...")
  */
}