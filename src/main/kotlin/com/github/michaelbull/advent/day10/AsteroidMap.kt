package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position
import java.util.ArrayDeque
import java.util.SortedMap

data class AsteroidMap(
    val asteroids: List<Position>
) {

    fun detectableAsteroids(from: Position): Int {
        return asteroids
            .filter { it != from }
            .map { from tangentTo it }
            .distinct()
            .size
    }

    fun vaporize(from: Position) = sequence {
        val rays = ArrayDeque(raycast(from).map(List<Position>::iterator))

        while (rays.isNotEmpty()) {
            val ray = rays.removeFirst()

            val vaporized = ray.next()
            yield(vaporized)

            if (ray.hasNext()) {
                rays.addLast(ray)
            }
        }
    }

    private infix fun Position.tangentTo(other: Position): Tangent {
        val adjacent = other.x - x
        val opposite = other.y - y
        return Tangent(adjacent, opposite).simplify()
    }

    private fun raycast(from: Position): List<List<Position>> {
        return asteroidsByTangent(from).values.map {
            it.sortedBy(from::distanceTo)
        }
    }

    private fun asteroidsByTangent(from: Position): SortedMap<Tangent, List<Position>> {
        return asteroids.asSequence()
            .filter { it != from }
            .groupBy { from tangentTo it }
            .toSortedMap(TangentComparator)
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
