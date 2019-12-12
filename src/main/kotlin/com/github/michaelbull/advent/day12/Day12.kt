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
        MoonsVisitor({ x }, { x }),
        MoonsVisitor({ y }, { y }),
        MoonsVisitor({ z }, { z })
    )

    for (moons in simulateMotion(this)) {
        val seenCombination = visitors.none { it.visit(moons) }

        if (seenCombination) {
            break
        }
    }

    return visitors
        .map { it.size.toLong() }
        .reduce(::leastCommonMultiple)
}

fun main() {
    val moons = readMoons()
    println("part 1 = ${moons.part1()}")
    println("part 2 = ${moons.part2()}")
}
