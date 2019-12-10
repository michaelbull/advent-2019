package com.github.michaelbull.advent.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10AnswersTest {

    private val map = readAsteroidMap()

    @Test
    fun answer1() {
        val bestPosition = Position(20, 21)
        val bestDetections = 247
        assertEquals(bestPosition to bestDetections, map.part1())
    }
}
