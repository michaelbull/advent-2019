package com.github.michaelbull.advent.intcode

sealed class ParameterMode {
    object Position : ParameterMode()
    object Immediate : ParameterMode()
    object Relative : ParameterMode()
}

fun Int.toParameterMode(): ParameterMode {
    return when (this) {
        0 -> ParameterMode.Position
        1 -> ParameterMode.Immediate
        2 -> ParameterMode.Relative
        else -> error("Unknown parameter mode $this")
    }
}
