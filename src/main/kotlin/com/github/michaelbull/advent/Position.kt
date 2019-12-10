package com.github.michaelbull.advent

import kotlin.math.atan2
import kotlin.math.hypot

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

    fun atan2(other: Position): Double {
        val deltaX = other.x - this.x
        val deltaY = other.y - this.y
        return atan2(deltaY.toDouble(), deltaX.toDouble())
    }

    fun hypot(other: Position): Double {
        val deltaX = other.x - this.x
        val deltaY = other.y - this.y
        return hypot(deltaX.toDouble(), deltaY.toDouble())
    }

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val ZERO = Position(0, 0)
    }
}
