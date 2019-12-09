package com.github.michaelbull.advent.day9

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day9ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "{0} should output {1}}")
    fun part1Examples(program: Intcode, expectedOutputs: List<Long>) = runBlockingTest {
        val computer = IntcodeComputer()
        val actualOutputs = mutableListOf<Long>()
        computer.memory = program
        computer.onOutput { actualOutputs += it }
        computer.compute()

        assertEquals(expectedOutputs, actualOutputs)
    }

    private data class ExampleArguments(
        val program: String,
        val expectedOutputs: List<Long>
    ) : Arguments {
        override fun get() = arrayOf(program.toIntcode(), expectedOutputs)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            ExampleArguments(
                program = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99",
                expectedOutputs = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99".split(",").map(String::toLong)
            ),
            ExampleArguments(
                program = "1102,34915192,34915192,7,4,7,99,0",
                expectedOutputs = listOf(1219070632396864)
            ),
            ExampleArguments(
                program = "104,1125899906842624,99",
                expectedOutputs = listOf(1125899906842624)
            )
        )
    }
}
