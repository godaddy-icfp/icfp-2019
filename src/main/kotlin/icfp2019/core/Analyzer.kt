package icfp2019.core

interface Analyzer<T> {
    fun analyze(map: GameBoard): (state: GameState) -> T
}
