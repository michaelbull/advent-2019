package com.github.michaelbull.advent.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12AnswersTest {

    private val moons = readMoons()

    @Test
    fun answer1() {
        assertEquals(14809, moons.part1())
    }

    @Test
    fun answer2() {
        assertEquals(282270365571288, moons.part2())
    }
}
