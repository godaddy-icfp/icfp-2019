package icfp2019.analyzers

import icfp2019.model.GameBoard
import icfp2019.model.GameState
import icfp2019.model.Node
import icfp2019.core.Analyzer2
import org.pcollections.PVector

class DistanceToWalls : Analyzer2<Int> {

    // Given a map, return a map of weights where the weight indicates the distance
    // the square is from the walls. We will sum the distance to close horizontal and vertical walls.

    private fun applyDistanceAlgorithm(map: PVector<PVector<Node>>): List<List<Int>> {
        val maxX = map.size
        val maxY = map[0].size
        val ret = map.mapIndexed { x, subArray ->
            subArray.mapIndexed { y, node ->
                if (node.isObstacle) {
                    -1
                } else {
                    val weightX = if (x < maxX / 2) x + 1 else maxX - x
                    val weightY = if (y < maxY / 2) y + 1 else maxY - y
                    weightX + weightY
                }
            }
        }
        return ret
    }

    override fun analyze(map: GameBoard): (robotIndex: Int, state: GameState) -> Int {
        val distanceBoard = applyDistanceAlgorithm(map.cells)
        return { robotIndex, state ->
            val robot = state.robotStateList[robotIndex]
            distanceBoard[robot.currentPosition.x][robot.currentPosition.y]
        }
    }
}