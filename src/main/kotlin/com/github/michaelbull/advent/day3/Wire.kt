package com.github.michaelbull.advent.day3

import com.github.michaelbull.advent.Position

data class Wire(
    val id: Int,
    val path: List<Step>
)

fun String.toWire(id: Int): Wire {
    val path = split(",").map(String::toStep)
    return Wire(id, path)
}

fun List<Wire>.stepsToIntersect(from: Position, to: Position): Int {
    return sumBy { wire -> wire.stepsToIntersect(from, to) }
}

fun Wire.stepsToIntersect(from: Position, to: Position): Int {
    var steps = 0
    var current = from

    for ((direction, length) in path) {
        val x = when (direction) {
            Direction.Right -> +1
            Direction.Left -> -1
            else -> 0
        }

        val y = when (direction) {
            Direction.Up -> +1
            Direction.Down -> -1
            else -> 0
        }

        repeat(length) {
            steps++
            current = current.translate(x, y)

            if (current == to) {
                return steps
            }
        }
    }

    throw IllegalArgumentException("no intersection found between $from and $to for $this")
}
