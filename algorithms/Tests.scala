package algo


object Tests extends App {
  // test case for union-finds
  unionfind.Tests.random(100000, 100000)
  unionfind.Tests.random(100000, 200000)
  unionfind.Tests.file("""G:\works\algorithms\AlgorithmsVarious\largeUF.txt""", skip = List("quick-find", "quick-union"))
}