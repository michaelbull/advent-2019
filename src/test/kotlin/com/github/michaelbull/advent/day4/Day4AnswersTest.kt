package com.github.michaelbull.advent.day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4AnswersTest {

    private val range = readRange()

    @Test
    fun answer1() {
        assertEquals(1019, range.validPasswords(::part1))
    }

    @Test
    fun answer2() {
        assertEquals(660, range.validPasswords(::part2))
    }
}
