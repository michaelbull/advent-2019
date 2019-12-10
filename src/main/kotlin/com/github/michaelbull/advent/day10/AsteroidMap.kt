package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position
import java.util.ArrayDeque

data class AsteroidMap(
    val asteroids: List<Position>
) {

    fun detectableAsteroids(from: Position): List<Position> {
        return asteroids
            .filter { to -> to != from }
            .map { to -> (to - from).simplify() }
            .distinct()
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

    private fun lines(from: Position): Collection<List<Position>> {
        val linesBySimplifiedDelta = asteroids.asSequence()
            .filter { to -> to != from }
            .groupBy { to -> (to - from).simplify() }

        return linesBySimplifiedDelta
            .mapValues { (_, line) -> line.sortedBy(from::distanceTo) }
            .toSortedMap(AngleComparator)
            .values
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
