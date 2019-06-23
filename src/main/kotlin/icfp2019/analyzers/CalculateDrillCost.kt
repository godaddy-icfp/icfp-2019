package icfp2019.analyzers

import icfp2019.Direction
import icfp2019.core.Analyzer
import icfp2019.model.DrillState
import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
import org.pcollections.PVector

object CalculateDrillCost : Analyzer<List<Array<DrillState>>> {
    override fun analyze(map: GameBoard): (state: GameState) -> List<Array<DrillState>> {

        val currentGrid = buildDrillRequiredFromEachNode(map.cells)
        return { gameState ->
            gameState.robotState.values.map { state ->
                currentGrid[state.currentPosition.x][state.currentPosition.y]
            }
        }
    }

    private fun buildDrillRequiredFromEachNode(currentGrid: PVector<PVector<Node>>): Array<Array<Array<DrillState>>> {

        val xMax = currentGrid.size
        val yMax = currentGrid[0].size

        return currentGrid.mapIndexed { x, row ->
            row.mapIndexed { y, _ ->
                if (!currentGrid[x][y].isObstacle) {
                    arrayOf(DrillState(Direction.E, -1))
                } else {
                    val rightDir =
                        (x until xMax).map {
                            currentGrid[it][y]
                        }
                            .takeWhile { it.isObstacle }
                            .count()
                    DrillState(Direction.R, rightDir)

                    val leftDir = (x downTo 0).map {
                        currentGrid[it][y]
                    }
                        .takeWhile { it.isObstacle }
                        .count()
                    DrillState(Direction.L, leftDir)

                    val upDir = (y until yMax).map {
                        currentGrid[x][it]
                    }
                        .takeWhile { it.isObstacle }
                        .count()
                    DrillState(Direction.U, upDir)

                    val downDir = (y downTo 0).map {
                        currentGrid[x][it]
                    }
                        .takeWhile { it.isObstacle }
                        .count()
                    DrillState(Direction.D, downDir)

                    arrayOf(
                        DrillState(Direction.R, rightDir),
                        DrillState(Direction.L, leftDir),
                        DrillState(Direction.U, upDir),
                        DrillState(Direction.D, downDir)
                    )
                }
            }.toTypedArray()
        }.toTypedArray()
    }
}
