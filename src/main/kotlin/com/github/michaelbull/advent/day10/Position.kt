package com.github.michaelbull.advent.day10

data class Position(
    val x: Int,
    val y: Int
) {

    operator fun minus(other: Position): Position {
        return Position(
            x = this.x - other.x,
            y = this.y - other.y
        )
    }
}
