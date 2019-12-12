package com.github.michaelbull.advent.day12

class MoonsVisitor(
    private val positionDimension: Position.() -> Int,
    private val velocityDimension: Velocity.() -> Int
) {

    private val visited = mutableSetOf<List<Pair<Int, Int>>>()

    val size: Int
        get() = visited.size

    fun visit(moons: List<Moon>): Boolean {
        return visited.add(moons.map(::pairDimensions))
    }

    private fun pairDimensions(moon: Moon): Pair<Int, Int> {
        return positionDimension(moon.position) to velocityDimension(moon.velocity)
    }
}
