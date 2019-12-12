package com.github.michaelbull.advent.day12

fun List<Moon>.visitor(
    positionDimension: Position.() -> Int,
    velocityDimension: Velocity.() -> Int
) = MoonsVisitor(this, positionDimension, velocityDimension)

class MoonsVisitor(
    initial: List<Moon>,
    private val positionDimension: Position.() -> Int,
    private val velocityDimension: Velocity.() -> Int
) {

    private val initialPositions = initial.map { it.position.positionDimension() }
    private val initialVelocities = initial.map { it.velocity.velocityDimension() }

    var cycleLength = 0L
        private set

    val finished: Boolean
        get() = cycleLength != 0L

    fun visit(step: Long, moons: List<Moon>) {
        val positions = moons.map { it.position.positionDimension() }
        val velocities = moons.map { it.velocity.velocityDimension() }

        if (positions == initialPositions && velocities == initialVelocities) {
            cycleLength = step
        }
    }
}
