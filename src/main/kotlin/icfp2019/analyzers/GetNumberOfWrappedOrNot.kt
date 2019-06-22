package icfp2019.analyzers

import icfp2019.Cell
import icfp2019.GameBoard
import icfp2019.GameState
import icfp2019.core.Analyzer

data class WrappedUnwrapped(val wrapped: Int, val unwrapped: Int)

object GetNumberOfWrappedOrNot : Analyzer<WrappedUnwrapped> {
    override fun analyze(map: GameBoard): (state: GameState) -> WrappedUnwrapped {
        return { _ ->
            map.rows()
                .flatten()
                .map {
                    if (Cell.hasFlag(it, Cell.WRAPPED)) {
                        WrappedUnwrapped(1, 0)
                    } else {
                        WrappedUnwrapped(0, 1)
                    }
                }
                .reduce { a, b -> WrappedUnwrapped(a.wrapped + b.wrapped, a.unwrapped + b.unwrapped) }
        }
    }
}
