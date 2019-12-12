package com.github.michaelbull.advent.day12

import kotlin.math.abs

data class Moon(
    val position: Position,
    val velocity: Velocity = Velocity.ZERO
) {

    val potentialEnergy: Int
        get() = abs(position.x) + abs(position.y) + abs(position.z)

    val kineticEnergy: Int
        get() = abs(velocity.x) + abs(velocity.y) + abs(velocity.z)

    val totalEnergy: Int
        get() = potentialEnergy * kineticEnergy
}

fun String.toMoon(): Moon {
    return Moon(this.toPosition())
}
