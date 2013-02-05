/** Union Find algorithms */
package algo.unionfind

import algo.utils._

trait UnionFind {
  def connected(p: Int, q: Int): Boolean
  def union(p: Int, q: Int): this.type
}

/** Quick-Find version of Union-Find:
  * Each node represented by index in array.
  * If nodes have the same content - they are connected
  *
  * @param n Number of nodes
  */
class QuickFind(n: Int) extends UnionFind with Printable {
  val set = Array.range(0, n)

  def connected(p: Int, q: Int) = set(p) == set(q)

  def union(p: Int, q: Int) = {
    if (!connected(p, q)){
      val (pv, qv) = (set(p), set(q))
      for (i <- 0 until set.length if set(i) == pv)
        set(i) = qv
    }

    this
  }

  def print() {
    (set.zipWithIndex groupBy { _._1 } values) map {
      _ map { _._2 } mkString ", "
    } foreach { x: String => println("{" + x + "}") }
  }
}


/** Quick-Union version of Union-Find:
  * Each node represented by index in array.
  * The values is a root (parent) of the node
  * If nodes have same parents they are connected
  *
  * @param n Number of nodes
  */
class QuickUnion(n: Int) extends UnionFind with Printable {
  val set = Array.range(0, n)

  protected def root(i: Int): Int = if (i == set(i)) i else root(set(i))

  def connected(p: Int, q: Int) = root(p) == root(q)

  def union(p: Int, q: Int) = {
    set(root(p)) = root(q)
    this
  }

  def print() {
    (0 until n groupBy root values) map { _ mkString ", " } foreach { x: String => println("{" + x + "}") }
  }
}


/** Weighted Quick union
  * Improvement of Quick-Union where we balancing
  * tree by adding smaller tree to the root of larger tree
  *
  * @param n Number of nodes
  */
class WeightedQuickUnion(n: Int) extends QuickUnion(n) {
  val sz = Array.fill(n)(1)

  override def union(p: Int, q: Int) = {
    val (i, j) = (root(p), root(q))
    if (sz(i) < sz(j)) { set(i) = j; sz(j) += sz(i) }
    else { set(j) = i; sz(i) += sz(j) }
    this
  }
}


/** Path Compression Quick-Union version of Union-Find:
  * Improvement of Quick-Union where we flattening the trees
  *
  * @param n Number of nodes
  */
class PathCompressionQuickUnion(n: Int) extends QuickUnion(n) {
  override protected def root(i: Int): Int =
    if (i == set(i)) i else { set(i) = set(set(i)); root(set(i)) }
}


/** Path Compression Weighted Quick-Union version of Union-Find
  *
  * @param n Number of nodes
  */
class PathCompressionWeightedQuickUnion(n: Int) extends WeightedQuickUnion(n) {
  override protected def root(i: Int): Int =
    if (i == set(i)) i else { set(i) = set(set(i)); root(set(i)) }
}