package com.github.michaelbull.advent.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6AnswersTest {

    private val map = readLocalOrbits()

    @Test
    fun answer1() {
        assertEquals(249308, map.totalOrbits())
    }

    @Test
    fun answer2() {
        assertEquals(349, map.minOrbitalTransfers(from = "YOU", to = "SAN"))
    }
}
