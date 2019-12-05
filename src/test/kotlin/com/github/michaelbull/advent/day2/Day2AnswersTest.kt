package com.github.michaelbull.advent.day2

import com.github.michaelbull.advent.intcode.IntcodeComputer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2AnswersTest {

    private val program = readIntcode()
    private val computer = IntcodeComputer()

    @Test
    fun answer1() {
        assertEquals(2894520, computer.part1(program))
    }

    @Test
    fun answer2() {
        assertEquals(9342, computer.part2(program))
    }
}
