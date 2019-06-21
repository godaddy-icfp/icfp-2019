package icfp2019

import icfp2019.external.*
import org.junit.jupiter.api.Test


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
    val enabled: BitMask = ParameterFeature.Path +
            ParameterFeature.Query +
            ParameterFeature.Header

    val disabled = enumValues<ParameterFeature>().filterNot { enabled.contains(it) }

    val expectedDisabled = List<ParameterFeature>(
            ParameterFeature.Undefined,
            ParameterFeature.Body,
            ParameterFeature.FormUnencoded,
            ParameterFeature.FormMultipart).map(it -> it.name)

    println("flags enabled: $enabled")
    println("flags disabled: $disabled")

    Assertions.assertEquals(result, disabled)
    println("mask hasFlag ParameterFeature.Query: ${enabled hasFlag ParameterFeature.Query}")
    println("mask hasFlag ParameterFeature.Body: ${enabled hasFlag ParameterFeature.Body}")
}