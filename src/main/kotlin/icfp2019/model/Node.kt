package icfp2019.model

data class Node(
    val point: Point,
    val isObstacle: Boolean,
    var isWrapped: Boolean = false,
    val booster: Booster? = null
) {
    fun hasBooster(booster: Booster): Boolean {
        if (this.booster != null) {
            return this.booster == booster
        }
        return false
    }
}
