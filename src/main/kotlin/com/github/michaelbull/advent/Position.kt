package com.github.michaelbull.advent

import kotlin.math.abs

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

    infix fun distanceTo(other: Position): Int {
        val deltaX = x - other.x
        val deltaY = y - other.y
        return abs(deltaX) + abs(deltaY)
    }

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val ZERO = Position(0, 0)
    }
}
