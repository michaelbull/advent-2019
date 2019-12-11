package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position
import java.util.ArrayDeque
import java.util.SortedMap

data class AsteroidMap(
    val asteroids: List<Position>
) {

    fun detectableAsteroids(from: Position): Int {
        return asteroids
            .filter { to -> to != from }
            .map { to -> (to - from).simplify() }
            .distinct()
            .size
    }

    fun vaporize(from: Position) = sequence {
        val lineIterators = lines(from).map(List<Position>::iterator)
        val queue = ArrayDeque(lineIterators)

        while (queue.isNotEmpty()) {
            val lineIterator = queue.removeFirst()

            val vaporized = lineIterator.next()
            yield(vaporized)

            if (lineIterator.hasNext()) {
                queue.addLast(lineIterator)
            }
        }
    }

    private operator fun Position.minus(other: Position): Tangent {
        val adjacent = this.x - other.x
        val opposite = this.y - other.y
        return Tangent(adjacent, opposite)
    }

    private fun lines(from: Position): Collection<List<Position>> {
        return asteroidsByTangent(from)
            .values
            .map { it.sortedBy(from::distanceTo) }
    }

    private fun asteroidsByTangent(from: Position): SortedMap<Tangent, List<Position>> {
        return asteroids.asSequence()
            .filter { to -> to != from }
            .groupBy { to -> (to - from).simplify() }
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
