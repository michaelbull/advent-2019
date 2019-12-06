package com.github.michaelbull.advent.day6

class OrbitMap(
    private val orbits: Map<String, String>
) : Map<String, String> by orbits {

    fun totalOrbits(): Int {
        return orbits.keys.fold(0) { acc, orbit ->
            var count = 0
            var currentOrbit = orbit

            while (currentOrbit in orbits) {
                count++
                currentOrbit = orbits.getValue(currentOrbit)
            }

            acc + count
        }
    }

    fun minOrbitalTransfers(from: String, to: String): Int {
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

    private fun orbitalTrail(orbit: String): List<String> {
        val trail = mutableListOf(orbit)
        var currentOrbit = orbit

        while (currentOrbit in orbits) {
            currentOrbit = orbits.getValue(currentOrbit)
            trail += currentOrbit
        }

        return trail
    }
}
