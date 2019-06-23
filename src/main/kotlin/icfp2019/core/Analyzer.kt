package icfp2019.core

import icfp2019.model.GameBoard
import icfp2019.model.GameState

interface Analyzer<T> {
    fun analyze(map: GameBoard): (state: GameState) -> T
}

interface Analyzer2<T> {
    fun analyze(map: GameBoard): (robotIndex : Int, state: GameState) -> T
}
