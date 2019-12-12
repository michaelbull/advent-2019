package com.github.michaelbull.advent.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12AnswersTest {

    private val jupiter = readPlanet()

    @Test
    fun answer1() {
        assertEquals(14809, jupiter.part1())
    }

    @Test
    fun answer2() {
        assertEquals(282270365571288, jupiter.part2())
    }
}
