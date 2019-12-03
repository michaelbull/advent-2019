package com.github.michaelbull.advent.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3AnswersTest {

    private val wires = readWires()

    private val grid = Grid().apply {
        wires.forEach(::plot)
    }

    @Test
    fun answer1() {
        assertEquals(2193, grid.part1())
    }

    @Test
    fun answer2() {
        assertEquals(63526, grid.part2(wires))
    }
}
