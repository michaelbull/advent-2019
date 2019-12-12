package com.github.michaelbull.advent.day12

fun List<Moon>.visitor(velocityDimension: Velocity.() -> Int) = MoonsVisitor(this, velocityDimension)

class MoonsVisitor(
    initial: List<Moon>,
    private val dimension: Velocity.() -> Int
) {

    var cycleLength: Long = 0L
        private set

    val finished: Boolean
        get() = cycleLength != 0L

    private var visits = 0L
    private val initial = initial.map { it.velocity.dimension() }

    fun visit(moons: List<Moon>) {
        val velocities = moons.map { it.velocity.dimension() }

        visits++

        if (velocities == initial) {
            /* https://www.reddit.com/r/adventofcode/comments/e9nqpq/day_12_part_2_2x_faster_solution/ */
            cycleLength = visits * 2
        }
    }
}
