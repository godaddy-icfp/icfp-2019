package icfp2019.model

data class Node(
    val point: Point,
    val isObstacle: Boolean,
    val isWrapped: Boolean = false,
    val boosters: Boosters? = null
) {
    fun hasBooster(boosters: Boosters): Boolean {
        if (this.boosters != null) {
            return this.boosters == boosters
        }
        return false
    }
}
