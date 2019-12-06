package com.github.michaelbull.advent.day6

fun readLocalOrbits(): OrbitMap {
    return ClassLoader.getSystemResourceAsStream("day6.txt")
        .bufferedReader()
        .readLines()
        .toOrbitMap()
}

fun List<String>.toOrbitMap(): OrbitMap {
    return map { it.split(")") }
        .associateBy({ it[1] }, { it[0] })
        .let(::OrbitMap)
}

fun main() {
    val map = readLocalOrbits()
    println("part 1 = ${map.totalOrbits()}")
    println("part 2 = ${map.minOrbitalTransfers(from = "YOU", to = "SAN")}")
}
