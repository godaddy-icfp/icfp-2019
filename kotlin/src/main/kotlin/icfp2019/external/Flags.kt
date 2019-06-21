package icfp2019.external

interface Flags  {
    val bit: Long

    fun toBitMask(): BitMask = BitMask(bit)
}