package com.github.michaelbull.advent.day6

typealias OrbitMap = Map<String, String>

fun readLocalOrbits(): OrbitMap {
    return ClassLoader.getSystemResourceAsStream("day6.txt")
        .bufferedReader()
        .readLines()
        .toOrbitMap()
}

fun List<String>.toOrbitMap(): OrbitMap {
    return map { line -> line.split(")").let { Pair(it[1], it[0]) } }.toMap()
}

fun OrbitMap.totalOrbits(): Int {
    return keys.fold(0) { acc, orbit ->
        var count = 0
        var currentOrbit = orbit

        while (currentOrbit in this) {
            count++
            currentOrbit = getValue(currentOrbit)
        }

        acc + count
    }
}

fun OrbitMap.minOrbitalTransfers(from: String, to: String): Int {
    require(from != to)

    val fromTrail = orbitalTrail(from)
    val toTrail = orbitalTrail(to)

    for ((i, objA) in fromTrail.withIndex()) {
        for ((j, objB) in toTrail.withIndex()) {
            if (objA == objB) {
                return i + j - 2
            }
        }
    }

    throw IllegalArgumentException("$from does not intersect $to")
}

fun OrbitMap.orbitalTrail(orbit: String): List<String> {
    val trail = mutableListOf(orbit)
    var currentOrbit = orbit

    while (currentOrbit in this) {
        currentOrbit = getValue(currentOrbit)
        trail += currentOrbit
    }

    return trail
}

fun main() {
    val map = readLocalOrbits()
    println("part 1 = ${map.totalOrbits()}")
    println("part 2 = ${map.minOrbitalTransfers(from = "YOU", to = "SAN")}")
}
