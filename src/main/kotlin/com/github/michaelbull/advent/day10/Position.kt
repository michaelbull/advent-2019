package com.github.michaelbull.advent.day10

import kotlin.math.atan2
import kotlin.math.hypot

data class Position(
    val x: Int,
    val y: Int
) {
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
}
