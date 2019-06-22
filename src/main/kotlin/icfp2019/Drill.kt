package icfp2019

fun getTotalDrillRequiredInRightDirectionScan(problem: Problem): Array<Array<Int>> {

    // filter { !it.isObstacle }
    val currentGrid = problem.map
    return currentGrid.mapIndexed { x, row ->
        row.mapIndexed { y, _ ->
            (x until problem.size.x).map {
                currentGrid[it][y]
            }
                .takeWhile { it.isObstacle }
                .count()
        }.toTypedArray()
    }.toTypedArray()
}
