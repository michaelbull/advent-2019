package com.github.michaelbull.advent.day8

sealed class Color {
    object Black : Color()
    object White : Color()
    object Transparent : Color()
}

fun Int.toColor(): Color {
    return when (this) {
        0 -> Color.Black
        1 -> Color.White
        2 -> Color.Transparent
        else -> error("Unknown color $this")
    }
}

fun Color.toChar(): Char {
    return when (this) {
        Color.Transparent -> ' '
        Color.White -> '#'
        Color.Black -> ' '
    }
}
