package com.github.michaelbull.advent.intcode

sealed class ParameterMode {
    object Position : ParameterMode()
    object Immediate : ParameterMode()
}

fun Int.toParameterMode(): ParameterMode {
    return when (this) {
        0 -> ParameterMode.Position
        1 -> ParameterMode.Immediate
        else -> error("Unknown parameter mode $this")
    }
}
