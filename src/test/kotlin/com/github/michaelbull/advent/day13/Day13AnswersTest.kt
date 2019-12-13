package com.github.michaelbull.advent.day13

import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13AnswersTest {

    private val game = readGame()

    @Test
    fun answer1() = runBlockingTest {
        assertEquals(304, game.part1())
    }

    @Test
    fun answer2() = runBlockingTest {
        assertEquals(14747, game.part2())
    }
}
