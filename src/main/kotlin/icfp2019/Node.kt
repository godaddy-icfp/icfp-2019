package icfp2019

data class Node(
    val point: Point,
    val isObstacle: Boolean,
    val isWrapped: Boolean = false,
    val booster: Booster? = null
) {
    fun hasBooster(booster: Booster): Boolean {
        if (this.booster != null) {
            return this.booster == booster
        }
        return false
    }
}
