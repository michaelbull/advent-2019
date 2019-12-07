package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.toIntcode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day7ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "max thruster signal {2} (from phase setting sequence {1}): {0}")
    fun part1Examples(program: Intcode, phaseSettings: IntArray, expectedSignal: Int) {
        val actualSignal = program.thrusterSignal(phaseSettings)
        assertEquals(expectedSignal, actualSignal)
    }

    private data class ExampleArguments(
        val program: String,
        val phaseSettings: IntArray,
        val expectedSignal: Int
    ) : Arguments {
        override fun get() = arrayOf(program.toIntcode(), phaseSettings, expectedSignal)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<ExampleArguments> = Stream.of(
            ExampleArguments(
                program = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0",
                phaseSettings = intArrayOf(4, 3, 2, 1, 0),
                expectedSignal = 43210
            ),
            ExampleArguments(
                program = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0",
                phaseSettings = intArrayOf(0, 1, 2, 3, 4),
                expectedSignal = 54321
            ),
            ExampleArguments(
                program = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0",
                phaseSettings = intArrayOf(1, 0, 4, 3, 2),
                expectedSignal = 65210
            )
        )
    }
}
