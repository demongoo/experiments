package algo.sort

import algo.utils._

object Tests {
  val algos = List("selection", "insertion", "shell", "merge", "bottom-up merge", "quick", "3-way quick")

  /** instantiates union factory */
  def factory[T : Ordering : ClassManifest](name: String)(data: Array[T]): Sort[T] = name match {
    case "selection" => new SelectionSort(data)
    case "insertion" => new InsertionSort(data)
    case "shell" => new ShellSort(data)
    case "merge" => new MergeSort(data)
    case "bottom-up merge" => new BottomUpMergeSort(data)
    case "quick" => new QuickSort(data)
    case "3-way quick" => new ThreeWayQuickSort(data)
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
      val (res, _) = ∮ {
        sort.sort()
      }
      assert(Sort.isSorted(copy))
      res
    }
  }

  /** Random simulation */
  case class RandomSimulation(n: Int, maxnum: Int = Int.MaxValue) extends Simulation {
    /** random data, although generated once to be consistent in all tests */
    val array = Array.fill(n)(util.Random.nextInt(maxnum))
  }

  /** Predefined data simulation */
  case class DataSimulation(array: Array[Int]) extends Simulation {
    val n = array.length
  }


  /** run random simulation */
  def random(n: Int, max: Int = Int.MaxValue, skip: List[String] = Nil) {
    println("<< Sort (" + n + ", < " + max + ", random) >>\n")
    simulation(RandomSimulation(n, max), skip)
  }

  /** run random simulation */
  def data(array: Array[Int], msg: String = "", skip: List[String] = Nil) {
    println("<< Data simulation (" + array.length + ", " + msg + ") >>\n")
    simulation(DataSimulation(array), skip)
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