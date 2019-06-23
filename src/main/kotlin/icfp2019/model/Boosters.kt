package icfp2019.model

sealed class Boosters {
    companion object {
        private val mapping = mapOf(
            'B' to ExtraArm,
            'F' to FastWheels,
            'L' to Drill,
            'X' to CloningLocation,
            'C' to CloneToken,
            'R' to Teleporter
        )

        val parseChars = mapping.keys.let { it.plus(it.map { it.toLowerCase() }) }.toSet()

        fun fromChar(code: Char): Booster = when (code) {
            in parseChars -> mapping.getValue(code.toUpperCase())
            else -> throw IllegalArgumentException("Unknown booster code: '$code'")
        }
    }

    object ExtraArm : Boosters()
    object FastWheels : Boosters()
    object Drill : Boosters()
    object Teleporter : Boosters()

    object CloningLocation : Boosters()
    object CloneToken : Boosters()
}
