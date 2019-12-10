package com.github.michaelbull.advent.day10

import kotlin.math.atan2

data class AsteroidMap(
    val asteroids: List<Position>
) {

    operator fun contains(position: Position): Boolean {
        return position in asteroids
    }

    fun detectableAsteroids(from: Position): Int {
        return asteroids
            .filter { to -> to != from }
            .map { to -> angle(from, to) }
            .distinct()
            .size
    }

    private fun angle(from: Position, to: Position): Double {
        val (deltaX, deltaY) = to - from
        return atan2(deltaY.toDouble(), deltaX.toDouble())
    }
}

fun String.toAsteroidMap(): AsteroidMap {
    val asteroids = mutableListOf<Position>()
    val lines = split("\n")

    for ((y, line) in lines.withIndex()) {
        for ((x, char) in line.withIndex()) {
            val position = Position(x, y)

            if (char == '#') {
                asteroids += position
            }
        }
    }

    return AsteroidMap(asteroids)
}
