package com.github.michaelbull.advent.day5

import com.github.michaelbull.advent.intcode.IntcodeComputer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5AnswersTest {

    private val program = readIntcode()

    @Test
    fun answer1() {
        var diagnosticCode = 0
        val computer = IntcodeComputer()
        computer.onOutput { diagnosticCode = it }
        computer.runTest(program, systemId = 1)
        assertEquals(15426686, diagnosticCode)
    }

    @Test
    fun answer2() {
        var diagnosticCode = 0
        val computer = IntcodeComputer()
        computer.onOutput { diagnosticCode = it }
        computer.runTest(program, systemId = 5)
        assertEquals(11430197, diagnosticCode)
    }
}
