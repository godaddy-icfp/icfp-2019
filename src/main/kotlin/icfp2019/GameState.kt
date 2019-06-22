package icfp2019

data class GameState(
    val gameBoard: GameBoard,
    val robotStateList: List<RobotState>,
    val teleportDestination: List<Point>,
    val unusedBoosters: List<Booster>
) {

    fun availableMoves(point: Point): List<Action> {
        val moves = mutableListOf<Action>()

        val currentPosition = robotStateList[0].currentPosition

        if (currentPosition.y + 1 < gameBoard.height) {
            val up = gameBoard.get(currentPosition.x, currentPosition.y + 1)
            if (!Cell.hasFlag(up, Cell.WRAPPED) && !Cell.hasFlag(up, Cell.OBSTACLE)) {
                moves.add(Action.MoveUp)
            }
        }

        if (currentPosition.y - 1 > -1) {
            val down = gameBoard.get(currentPosition.x, currentPosition.y - 1)
            if (!Cell.hasFlag(down, Cell.WRAPPED) && !Cell.hasFlag(down, Cell.OBSTACLE)) {
                moves.add(Action.MoveDown)
            }
        }

        if (currentPosition.x - 1 > -1) {
            val left = gameBoard.get(currentPosition.x - 1, currentPosition.y)
            if (!Cell.hasFlag(left, Cell.WRAPPED) && !Cell.hasFlag(left, Cell.OBSTACLE)) {
                moves.add(Action.MoveLeft)
            }
        }

        if (currentPosition.x + 1 < gameBoard.width) {
            val right = gameBoard.get(currentPosition.x + 1, currentPosition.y)
            if (!Cell.hasFlag(right, Cell.WRAPPED) && !Cell.hasFlag(right, Cell.OBSTACLE)) {
                moves.add(Action.MoveRight)
            }
        }

        return moves
    }
}
