package com.github.michaelbull.advent.day7

import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7AnswersTest {

    private val software = readSoftware()

    @Test
    fun answer1() = runBlockingTest {
        assertEquals(13848, software.part1())
    }

    @Test
    fun answer2() = runBlockingTest {
        assertEquals(12932154, software.part2())
    }
}
