package com.github.michaelbull.advent.day3

sealed class Direction {
    object Up : Direction()
    object Down : Direction()
    object Left : Direction()
    object Right : Direction()
}

fun Char.toDirection(): Direction {
    return when (this) {
        'U' -> Direction.Up
        'D' -> Direction.Down
        'L' -> Direction.Left
        'R' -> Direction.Right
        else -> error("Unknown direction $this")
    }
}
