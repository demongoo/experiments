package algo.unionfind

import algo.utils._

object Tests {
  val algos = List("quick-find", "quick-union", "weighted quick-union", "path compression quick-union", "path compression weighted quick-union")

  /** instantiates union factory */
  def factory(name: String)(n: Int) = name match {
    case "quick-find" => new QuickFind(n)
    case "quick-union" => new QuickUnion(n)
    case "weighted quick-union" => new WeightedQuickUnion(n)
    case "path compression quick-union" => new PathCompressionQuickUnion(n)
    case "path compression weighted quick-union" => new PathCompressionWeightedQuickUnion(n)
  }

  /** generate simulation data: number of elements, number of unions, number of tests for connection */
  case class Simulation(n: Int, nu: Int, nc: Int) {

    val unions = rseq(nu)
    val contests = rseq(nc)

    private def rseq(num: Int) = Seq.fill(nu)((util.Random.nextInt(n), util.Random.nextInt(n)))

    def apply(name: String): (Long, Seq[Boolean]) = {
      val uf = factory(name)(n)
      ∮ {
        unions foreach { case (p, q) => uf union (p, q) }
        contests map { case (p, q) => uf connected (p, q) }
      }
    }
  }
  object Simulation {
    def apply(n: Int): Simulation = Simulation(n, (n * 0.5).toInt, (n * 0.5).toInt)
    def apply(n: Int, nu: Int): Simulation = Simulation(n, nu, (n * 0.5).toInt)
  }

  /** do the tests for each algorithm on the same n sized simulation */
  def apply(n: Int)(nu: Int = (n * 0.5).toInt, nc: Int = (n * 0.5).toInt) {
    println("<< Union-Find (" + n + ") >>\n")

    val sim = Simulation(n)
    val results = algos map { alg =>
      sim(alg) match {
        case (time, res) =>
          println(alg + ": " + (time ms))
          (alg, time, res)
      }
    }

    // ranking by performance
    println("\nPerformance ranking:")
    results sortBy { _._2 } foreach {
      case (alg, time, _) => println(alg + ": " + (time ms))
    }

    val errors = sim.contests zip (results map { _._3 } transpose) filter { case (_, xs) => xs.exists(_ != xs.head) }
    if (errors != Nil) println("Errors in connected", errors)
  }
}