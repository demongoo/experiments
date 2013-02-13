package gt

object Tests extends App {
  testCoalitional()

  def testCoalitional() {
    import coalitional.Game
    /*
    // basic game
    new Game(2) {
      // definition
      v(1) = 0
      v(2) = 2
      v(1,2) = 2

      // print Shapley values for each player
      println((1 to N) map ϕ)
    }

    // more advanced game
    new Game(3) {
      v(1) = 0
      v(2) = 0
      v(3) = 0
      v(1,2) = 90
      v(1,3) = 80
      v(2,3) = 70
      v(1,2,3) = 120

      // Shapley value: 45, 40, 35
      println((1 to N) map ϕ)
    }

    // basic with algorithmic payoff
    new Game(3) {
      v(S) = {
        case s: Coalition if s.contains(1) && s.size >= 2 => 1
        case _ => 0
      }: PayoffFunction

      // Shapley value: 2/3, 1/6, 1/6
      println((1 to N) map ϕ)
    }

    // one more
    new Game(3) {
      val c = 1
      val w1 = 2
      val w2 = 3

      v(S) = {
        case s: Coalition if (Set(c, w1) &~ s).isEmpty => 3
        case s: Coalition if (Set(c, w2) &~ s).isEmpty => 3
        case s: Coalition if (Set(c, w2, w2) &~ s).isEmpty => 4
        case _ => 0
      }: PayoffFunction

      println((1 to N) map ϕ)
    }
    */
    // 4 players
    new Game(4) {
      val A = 1
      val B = 2
      val C = 3
      val D = 4
      val voices = Map(A -> 45, B -> 25, C -> 15, D -> 15)

      v(S) = {
        case s: Coalition =>
          if ((s.toList map voices).sum > 50) 100
          else 0
      }: PayoffFunction

      println((1 to N) map ϕ)
    }

    new Game(3) {
      v(1) = v(2) = v(3) = 1
      v(1,2) = 3
      v(1,3) = 4
      v(2,3) = 5
      v(1,2,3) = 7

      println((1 to N) map ϕ)
      println(isSuperadditive)
    }

    new Game(3) {
      v(1) = v(2) = v(3) = 1
      v(1,2) = 3
      v(1,3) = 4
      v(2,3) = 5
      v(1,2,3) = 5

      println((1 to N) map ϕ)
      println(isSuperadditive)
      println(isConvex)
    }

    new Game(3) {
      v(1) = v(2) = v(3) = 0
      v(1,2) = v(2,3) = v(3,1) = 2/3D
      v(1,2,3) = 1

      println(isInCore(List(0, 0, 0)))
      println(isInCore(List(1/3D, 1/3D, 0)))
      println(isInCore(List(1/3D, 1/3D, 1/3D)))
      println(isConvex)
    }
  }
}