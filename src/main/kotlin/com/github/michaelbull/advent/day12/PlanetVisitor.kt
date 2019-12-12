package com.github.michaelbull.advent.day12

class PlanetVisitor(
    planet: Planet,
    private val dimension: Velocity.() -> Int
) {

    var cycleLength: Long = 0L
        private set

    val finished: Boolean
        get() = cycleLength != 0L

    private var visits = 0L
    private val initial = planet.initialVelocities()

    fun visit(planet: Planet) {
        val velocities = planet.initialVelocities()

        visits++

        if (velocities == initial) {
            /* https://www.reddit.com/r/adventofcode/comments/e9nqpq/day_12_part_2_2x_faster_solution/ */
            cycleLength = visits * 2
        }
    }

    private fun Planet.initialVelocities(): List<Int> {
        return moons.map { it.velocity.dimension() }
    }
}
