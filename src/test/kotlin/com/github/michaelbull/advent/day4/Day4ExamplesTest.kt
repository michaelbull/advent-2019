package com.github.michaelbull.advent.day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day4ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "{0} = {1}")
    fun part1Examples(password: Int, expected: Boolean) {
        assertEquals(expected, part1(password))
    }

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "{0} = {1}")
    fun part2Examples(password: Int, expected: Boolean) {
        assertEquals(expected, part2(password))
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of(111111, true),
            Arguments.of(223450, false),
            Arguments.of(123789, false)
        )
    }

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of(112233, true),
            Arguments.of(123444, false),
            Arguments.of(111122, true)
        )
    }
}
