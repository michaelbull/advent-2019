package com.github.michaelbull.advent.day6

fun readLocalOrbits(): OrbitMap {
    return ClassLoader.getSystemResourceAsStream("day6.txt")
        .bufferedReader()
        .readLines()
        .toOrbitMap()
}

fun List<String>.toOrbitMap(): OrbitMap {
    val orbits = map { line ->
        val parts = line.split(")")
        val obj = parts[0]
        val orbiter = parts[1]
        Pair(orbiter, obj)
    }.toMap()

    return OrbitMap(orbits)
}

fun main() {
    val map = readLocalOrbits()
    println("part 1 = ${map.totalOrbits()}")
    println("part 2 = ${map.minOrbitalTransfers(from = "YOU", to = "SAN")}")
}
