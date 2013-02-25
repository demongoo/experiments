package algo.sort

import algo.utils._

object Tests {
  val algos = List("selection", "insertion", "shell", "merge", "bottom-up merge")

  /** instantiates union factory */
  def factory[T : Ordering : ClassManifest](name: String)(data: Array[T]): Sort[T] = name match {
    case "selection" => new SelectionSort(data)
    case "insertion" => new InsertionSort(data)
    case "shell" => new ShellSort(data)
    case "merge" => new MergeSort(data)
    case "bottom-up merge" => new BottomUpMergeSort(data)
  }

  /** Simulation of a data for sorts */
  trait Simulation {
    /** number of elements */
    val n: Int

    /** array should be generated only once */
    val array: Array[Int]

    /** run round of simulation */
    def apply(name: String): Long = {
      val copy = array.clone()
      val sort = factory(name)(copy)
      ∮ {
        sort.sort()
      } _1
    }
  }

  /** Random simulation */
  case class RandomSimulation(n: Int, maxnum: Int = Int.MaxValue) extends Simulation {
    /** random data, although generated once to be consistent in all tests */
    val array = Array.fill(n)(util.Random.nextInt(maxnum))
  }


  /** run random simulation */
  def random(n: Int, max: Int = Int.MaxValue, skip: List[String] = Nil) {
    println("<< Sort (" + n + ", < " + max + ", random) >>\n")
    simulation(RandomSimulation(n, max), skip)
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