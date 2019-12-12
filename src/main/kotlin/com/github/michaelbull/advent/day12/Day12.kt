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

fun main() {
    val moons = readMoons()
    println("part 1 = ${moons.part1()}")
}
