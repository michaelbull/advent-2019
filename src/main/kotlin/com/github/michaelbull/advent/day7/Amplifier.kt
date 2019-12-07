package com.github.michaelbull.advent.day7

data class Amplifier(
    val id: Char,
    val phaseSetting: Int
)

fun List<Int>.toAmplifiers(): List<Amplifier> {
    return mapIndexed { index, phaseSetting ->
        Amplifier('A' + index, phaseSetting)
    }
}
