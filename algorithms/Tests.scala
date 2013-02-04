package algo

import unionfind._

/* to write test case with reasonably large set of data to measure performance */

object Tests extends App {
  def testUnionFind(uf: UnionFind with Printable) {
    uf union (2, 3) union (5, 9) union (0, 7) union (1, 9) union (2, 3) union (0, 3)
    uf.print()
    println("Connected 0 <-> 7", uf connected (0, 7))
    println("Connected 0 <-> 8", uf connected (0, 8))
  }

  {
    println("Test Quick-Find")
    testUnionFind(new QuickFind(10))
  }

  {
    println("Test Quick-Union")
    testUnionFind(new QuickUnion(10))
  }

  {
    println("Test Weighted Quick-Union")
    testUnionFind(new WeightedQuickUnion(10))
  }

  {
    println("Path Compression Quick-Union")
    testUnionFind(new PathCompressionQuickUnion(10))
  }

  {
    println("Path Compression Weighted Quick-Union")
    testUnionFind(new PathCompressionWeightedQuickUnion(10))
  }
}