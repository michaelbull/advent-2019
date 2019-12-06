package com.github.michaelbull.advent.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6ExamplesTest {

    @Test
    fun part1Example() {
        val map = listOf(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L"
        ).toOrbitMap()

        assertEquals(42, map.totalOrbits())
    }

    @Test
    fun part2Example() {
        val map = listOf(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L",
            "K)YOU",
            "I)SAN"
        ).toOrbitMap()

        assertEquals(4, map.minOrbitalTransfers(from = "YOU", to = "SAN"))
    }
}
