package algo.unionfind

import algo.utils._
import io.Source

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

  /** Simulation of a data for unions */
  trait Simulation {
    /** number of elements */
    val n: Int

    /** source of p & q */
    val unions: { def toIterator: Iterator[(Int, Int)] }

    /** does initialization of simulation: rewinds iterators, etc */
    def init()

    /** run round of simulation */
    def apply(name: String): Long = {
      init()
      val uf = factory(name)(n)
      ∮ {
        unions.toIterator foreach {
          case (p, q) => if (!uf.connected(p, q)) uf.union(p, q)
        }
      } _1
    }
  }

  /** Random simulation */
  case class RandomSimulation(n: Int, nu: Int) extends Simulation {
    /** random data, although generated once to be consistent in all tests */
    val unions = Seq.fill(nu)((util.Random.nextInt(n), util.Random.nextInt(n)))

    /** init is nothing (just funny to return parenthesis :)) */
    def init() = ()
  }

  /** Simulation using data from file */
  case class FileSimulation(name: String) extends Simulation {
    /** first line in file is n */
    val n = (Source.fromFile(name).getLines take 1).toList.head.toInt

    /** next lines are pairs */
    val unions = (Source.fromFile(name).getLines drop 1) map {
      s => val Array(p, q) = s.trim.split(' ') map (_.toInt); (p, q)
    } toSeq

    /** init is nothing (just funny to return parenthesis :)) */
    def init() = ()
  }


  /** run random simulation */
  def random(n: Int, nu: Int, skip: List[String] = Nil) {
    println("<< Union-Find (" + n + ", " + nu + ", random) >>\n")
    simulation(RandomSimulation(n, nu), skip)
  }


  /** run simulation from file data */
  def file(fname: String, skip: List[String] = Nil) {
    val fsize = new java.io.File(fname).length
    println("<< Union-Find (" + fname + ", " + (fsize / 1000) + " kbytes) >>\n")
    simulation(FileSimulation(fname), skip)
  }

  /** simulation process */
  private def simulation(sim: Simulation, skip: List[String] = Nil) {
    val results = algos filterNot { skip contains _ } map { alg =>
      val time = sim(alg)
      println(alg + ": " + (time ms))
      (alg, time)
    }

    // ranking by performance
    println("\nPerformance ranking:")
    results sortBy { _._2 } foreach {
      case (alg, time) => println(alg + ": " + (time ms))
    }
  }
}