package icfp2019

// Move to an open space and push moves onto a stack, if no moves available then backtrack using the stack
class BackTrackingStrategy : Strategy {
    override fun getActions(gameState: GameState): Map<Int, List<Action>> {
        // initialize robot stacks
        // initialize robot move lists

        // for each robot:
            // get available squares
            // if no available squares
                // if robot stack is empty
                    // continue, robot is done
                // else
                    // pop from stack, apply inverse action
        return HashMap<Int, List<Action>>()
    }
}