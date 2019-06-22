package icfp2019

data class Point(val x: Int, val y: Int) {
    fun isNeighbor(otherPoint: Point): Boolean {
        return listOf(
            Point(x - 1, y),
            Point(x + 1, y),
            Point(x, y - 1),
            Point(x, y + 1),
            Point(x - 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y - 1),
            Point(x + 1, y + 1)
        ).contains(otherPoint)
    }

    fun up(): Point {
        return copy(y = y + 1)
    }

    fun down(): Point {
        return copy(y = y - 1)
    }

    fun left(): Point {
        return copy(x = x - 1)
    }

    fun right(): Point {
        return copy(x = x + 1)
    }
}
