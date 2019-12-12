package com.github.michaelbull.advent.day12

fun readMoons(): List<Moon> {
    return ClassLoader.getSystemResourceAsStream("day12.txt")
        .bufferedReader()
        .useLines { it.map(String::toMoon).toList() }
}

fun List<Moon>.totalEnergy(): Int {
    return sumBy(Moon::totalEnergy)
}

fun List<Moon>.part1(): Int {
    return simulateMotion(this).elementAt(999).totalEnergy()
}

fun List<Moon>.part2(): Long {
    val visitors = listOf(
        visitor { x },
        visitor { y },
        visitor { z }
    )

    for (moons in simulateMotion(this)) {
        visitors.filterNot(MoonsVisitor::finished).forEach {
            it.visit(moons)
        }

        if (visitors.all(MoonsVisitor::finished)) {
            break
        }
    }

    return visitors
        .map(MoonsVisitor::cycleLength)
        .reduce(::leastCommonMultiple)
}

fun main() {
    val moons = readMoons()
    println("part 1 = ${moons.part1()}")
    println("part 2 = ${moons.part2()}")
}
