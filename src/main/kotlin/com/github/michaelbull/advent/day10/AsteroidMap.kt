package com.github.michaelbull.advent.day10

import kotlin.math.PI

data class AsteroidMap(
    val asteroids: List<Position>
) {

    operator fun contains(position: Position): Boolean {
        return position in asteroids
    }

    fun detectableAsteroids(from: Position): Int {
        return asteroids
            .filter { to -> to != from }
            .map(from::atan2)
            .distinct()
            .size
    }

    fun vaporizeFrom(station: Position, count: Int): Position {
        val remaining = asteroids.filter { it != station }.toMutableList()
        val angleComparator = compareBy(Map.Entry<Double, List<Position>>::key)

        var currentAngle = -PI / 2
        var moved = false
        lateinit var lastVaporized: Position

        repeat(count) {
            val asteroidsByAngle = remaining.groupBy(station::atan2)

            val (nextAngle, asteroidsOnLine) = asteroidsByAngle
                .filter { (angle, _) -> if (moved) angle > currentAngle else angle >= currentAngle }
                .minWith(angleComparator)
                ?: asteroidsByAngle.minWith(angleComparator)!!

            currentAngle = nextAngle
            moved = true

            val firstAsteroidOnLine = asteroidsOnLine.minBy(station::hypot)
            if (firstAsteroidOnLine != null) {
                remaining.remove(firstAsteroidOnLine)
                lastVaporized = firstAsteroidOnLine
            }
        }

        return lastVaporized
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
