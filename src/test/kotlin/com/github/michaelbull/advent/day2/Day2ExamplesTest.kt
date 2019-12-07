package com.github.michaelbull.advent.day2

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

class Day2ExamplesTest {

    private val computer = IntcodeComputer()

    @ArgumentsSource(Examples::class)
    @ParameterizedTest(name = "{0} becomes {1}")
    fun examples(program: String, expected: String) = runBlockingTest {
        computer.memory = program.toIntcode()
        computer.compute()

        val actual = computer.memory.joinToString(",")
        assertEquals(expected, actual)
    }

    private class Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of("1,0,0,0,99", "2,0,0,0,99"),
            Arguments.of("2,3,0,3,99", "2,3,0,6,99"),
            Arguments.of("2,4,4,5,99,0", "2,4,4,5,99,9801"),
            Arguments.of("1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99")
        )
    }
}
