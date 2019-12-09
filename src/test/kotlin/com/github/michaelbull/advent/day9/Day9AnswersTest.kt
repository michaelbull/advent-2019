package com.github.michaelbull.advent.day9

import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9AnswersTest {

    private val boost = readBoost()

    @Test
    fun answer1() = runBlockingTest {
        assertEquals(2745604242, boost.part1())
    }

    @Test
    fun answer2() = runBlockingTest {
        assertEquals(51135, boost.part2())
    }
}
