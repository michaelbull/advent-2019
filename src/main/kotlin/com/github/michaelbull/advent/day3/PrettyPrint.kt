package com.github.michaelbull.advent.day3

import com.github.michaelbull.advent.Position

fun Grid.prettyPrint(width: Int, height: Int): String {
    val builder = StringBuilder()

    for (y in height - 1 downTo 0) {
        for (x in 0 until width) {
            builder.append(this[Position(x, y)].toChar())
        }

        if (y != 0) {
            builder.append("\n")
        }
    }

    return builder.toString()
}

fun Cell.toChar(): Char {
    return when (this) {
        Cell.Empty -> '.'
        Cell.CentralPort -> 'o'
        Cell.Intersection -> 'X'
        is Cell.WireHorizontal -> '-'
        is Cell.WireVertical -> '|'
        is Cell.WireCorner -> '+'
    }
}
