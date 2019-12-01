package com.github.michaelbull.advent.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1AnswersTest {

    private val input = readModules()

    @Test
    fun answer1() {
        assertEquals(3159380, part1(input))
    }

    @Test
    fun answer2() {
        assertEquals(4736213, part2(input))
    }
}
