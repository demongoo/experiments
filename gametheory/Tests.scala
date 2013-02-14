package gt

object Tests extends App {
  testCoalitional()

  def testCoalitional() {
    import coalitional.Game

    // examples are taken from Game Theory course in-video quizzes
    // they are not graded, hope I don't do anything wrong, just say

    // Suppose N=3 and v(1)=v(2)=v(3)=1. Which of the following payoff functions is superadditive?
    // a) v(1,2)=3, v(1,3)=4, v(2,3)=5, v(1,2,3)=5;
    // b) v(1,2)=3, v(1,3)=4, v(2,3)=5, v(1,2,3)=7;
    // c) v(1,2)=0, v(1,3)=4, v(2,3)=5, v(1,2,3)=7;
    // d) None of the above.
    {
      class ThreeGame extends Game(3) {
        v(1) = v(2) = v(3) = 1
      }

      val a = new ThreeGame {
        v(1,2) = 3
        v(1,3) = 4
        v(2,3) = 5
        v(1,2,3) = 5
      }

      val b = new ThreeGame {
        v(1,2) = 3
        v(1,3) = 4
        v(2,3) = 5
        v(1,2,3) = 7
      }

      val c = new ThreeGame {
        v(1,2) = 0
        v(1,3) = 4
        v(2,3) = 5
        v(1,2,3) = 7
      }

      println(a.isSuperadditive, b.isSuperadditive, c.isSuperadditive)
    }

    // Suppose N=2 and v(1)=0, v(2)=2, v(1,2)=2. What is the Shapley Value of both players?
    new Game(2) {
      v(1) = 0
      v(2) = 2
      v(1,2) = 2

      println(1 to N map ϕ)
    }

    // Suppose N=3 and v(1)=v(2)=v(3)=0, v(1,2)=v(2,3)=v(3,1)=2/3, v(1,2,3)=1
    // Which allocation is in the core of this coalitional game?
    // a) (0,0,0);
    // b) (1/3, 1/3, 0);
    // c) (1/3, 1/3, 1/3);
    // d) None of the above.
    new Game(3) {
      v(1) = v(2) = v(3) = 0
      v(1,2) = v(2,3) = v(3,1) = 2/3D
      v(1,2,3) = 1

      println(
        isInCore(0, 0, 0),
        isInCore(1/3D, 1/3D, 0),
        isInCore(1/3D, 1/3D, 1/3D)
      )
    }
  }
}