Some stuff for solving games from Game Theory

Honestly I did it to understand what the hell is the core, and because I like to program, especially in Scala.

## Coalitional games
Coalitional games are represented by (N, v), where
* `N` - set of players,
* `v` - characteristic function: associates with each coalition S ⊆ N a real-valued payoff v(S)
  that the coalition’s members can distribute among themselves

Capabilities: Shapley value, tests for superadditivity, core, convexity

Example usage:

    val g = new Game(3) {
      // setup game
      v(1) = v(2) = v(3) = 1
      v(1,2) = 3
      v(1,3) = 4
      v(2,3) = 5
      v(1,2,3) = 5

      // also possible to use characteristic function
      v(S) = {
        case s: Coalition => ...
      }: PayoffFunction
    }

    // get shapley values
    println((1 to g.N) map g.ϕ)
    // test superadditivity and convexity
    println(g.isSuperadditive)
    println(g.isConvex)
    // test if payoff vector is in the core
    println(g isInCore List(1.0, 2.0, 3.0))