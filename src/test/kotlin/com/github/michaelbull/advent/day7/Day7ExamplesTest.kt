package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.test.runBlockingTest
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
    fun part1Examples(program: Intcode, phaseSettings: List<Int>, expectedSignal: Long) = runBlockingTest {
        val actualSignal = program.thrusterSignal(phaseSettings.toAmplifiers())
        assertEquals(expectedSignal, actualSignal)
    }

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "max thruster signal with feedback loop {2} (from phase setting sequence {1}): {0}")
    fun part2Examples(program: Intcode, phaseSettings: List<Int>, expectedSignal: Long) = runBlockingTest {
        val actualSignal = program.feedbackLoopThrusterSignal(phaseSettings.toAmplifiers())
        assertEquals(expectedSignal, actualSignal)
    }

    private data class ExampleArguments(
        val program: String,
        val phaseSettings: List<Int>,
        val expectedSignal: Long
    ) : Arguments {
        override fun get() = arrayOf(program.toIntcode(), phaseSettings, expectedSignal)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<ExampleArguments> = Stream.of(
            ExampleArguments(
                program = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0",
                phaseSettings = listOf(4, 3, 2, 1, 0),
                expectedSignal = 43210
            ),
            ExampleArguments(
                program = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0",
                phaseSettings = listOf(0, 1, 2, 3, 4),
                expectedSignal = 54321
            ),
            ExampleArguments(
                program = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0",
                phaseSettings = listOf(1, 0, 4, 3, 2),
                expectedSignal = 65210
            )
        )
    }

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<ExampleArguments> = Stream.of(
            ExampleArguments(
                program = listOf(
                    "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,",
                    "27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
                ).joinToString(separator = ""),
                phaseSettings = listOf(9, 8, 7, 6, 5),
                expectedSignal = 139629729
            ),
            ExampleArguments(
                program = listOf(
                    "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,",
                    "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,",
                    "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
                ).joinToString(separator = ""),
                phaseSettings = listOf(9, 7, 8, 5, 6),
                expectedSignal = 18216
            )
        )
    }
}
