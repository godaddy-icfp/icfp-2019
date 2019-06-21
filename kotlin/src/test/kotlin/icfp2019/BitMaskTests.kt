package icfp2019

import icfp2019.external.*

enum class ParameterFeature(override val bit: Long) : Flags {
    Undefined(0),
    Path(1 shl 0),
    Query(1 shl 1),
    Header(1 shl 2),
    Body(1 shl 3),
    FormUnencoded(1 shl 4),
    FormMultipart(1 shl 5);
}

@Test
fun testBitMask(args : Array<String>){
    val mask: BitMask = ParameterFeature.Path +
            ParameterFeature.Query +
            ParameterFeature.Header;

    val enabled = enabledValues<ParameterFeature>(mask)

    println("flags enabled: $enabled")
    println("flags disabled: ${enumValues<ParameterFeature>().filterNot { enabled.contains(it) } }")
    println("mask hasFlag ParameterFeature.Query: ${mask hasFlag ParameterFeature.Query}")
    println("mask hasFlag ParameterFeature.Body: ${mask hasFlag ParameterFeature.Body}")
}