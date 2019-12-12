package com.github.michaelbull.advent.day12

data class Planet(
    val moons: List<Moon>
) {

    val totalEnergy: Int
        get() = moons.sumBy(Moon::totalEnergy)

    fun visitor(dimension: Velocity.() -> Int): PlanetVisitor {
        return PlanetVisitor(this, dimension)
    }
}

fun Iterable<String>.toPlanet(): Planet {
    return Planet(map(String::toMoon))
}
