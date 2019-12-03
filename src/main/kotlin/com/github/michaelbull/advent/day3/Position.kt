package com.github.michaelbull.advent.day3

data class Position(
    val x: Int,
    val y: Int
) {

    fun translate(x: Int = 0, y: Int = 0): Position {
        return copy(
            x = this.x + x,
            y = this.y + y
        )
    }

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val ZERO = Position(0, 0)
    }
}
