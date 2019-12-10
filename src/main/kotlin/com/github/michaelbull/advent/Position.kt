package com.github.michaelbull.advent

import com.github.michaelbull.advent.day10.greatestCommonDivisor
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

    operator fun plus(other: Position): Position {
        return copy(
            x = this.x + other.x,
            y = this.y + other.y
        )
    }

    operator fun minus(other: Position): Position {
        return copy(
            x = this.x - other.x,
            y = this.y - other.y
        )
    }

    operator fun div(divisor: Int): Position {
        return copy(
            x = this.x / divisor,
            y = this.y / divisor
        )
    }

    fun simplify(): Position {
        return this / greatestCommonDivisor(x, y)
    }

    infix fun distanceTo(other: Position): Int {
        val deltaX = x - other.x
        val deltaY = y - other.y
        return abs(deltaX) + abs(deltaY)
    }

    infix fun cross(other: Position): Long {
        return x.toLong() * other.y - y.toLong() * other.x
    }

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val ZERO = Position(0, 0)
    }
}
