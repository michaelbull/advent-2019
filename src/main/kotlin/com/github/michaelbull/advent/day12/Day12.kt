package com.github.michaelbull.advent.day12

fun readPlanet(): Planet {
    return ClassLoader.getSystemResourceAsStream("day12.txt")
        .bufferedReader()
        .readLines()
        .toPlanet()
}

fun Planet.part1(): Int {
    return simulateMotion(this).elementAt(999).totalEnergy
}

fun Planet.part2(): Long {
    val visitors = listOf(
        visitor { x },
        visitor { y },
        visitor { z }
    )

    for (planet in simulateMotion(this)) {
        visitors.filterNot(PlanetVisitor::finished).forEach {
            it.visit(planet)
        }

        if (visitors.all(PlanetVisitor::finished)) {
            break
        }
    }

    return visitors
        .map(PlanetVisitor::cycleLength)
        .reduce(::leastCommonMultiple)
}

fun main() {
    val jupiter = readPlanet()
    println("part 1 = ${jupiter.part1()}")
    println("part 2 = ${jupiter.part2()}")
}
