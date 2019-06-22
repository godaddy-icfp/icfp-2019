package icfp2019

class GameState
class Action

interface Strategy {
    // Given a state, return a list of actions, indexed by Robot id.
    fun getActions(gameState: GameState): Map<Int, Action>
}