package com.github.michaelbull.advent.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day1ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "for a mass of {0}, the fuel required is {1}")
    fun part1Examples(mass: Int, expectedFuel: Int) {
        assertEquals(expectedFuel, FuelCounterUpper.fuelToLaunch(Module(mass)))
    }

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "for a mass of {0}, the fuel required is {1}")
    fun part2Examples(mass: Int, expectedFuel: Int) {
        assertEquals(expectedFuel, RocketEquationDoubleChecker.fuelToLaunch(Module(mass)))
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of(12, 2),
            Arguments.of(14, 2),
            Arguments.of(1969, 654),
            Arguments.of(100756, 33583)
        )
    }

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of(14, 2),
            Arguments.of(1969, 966),
            Arguments.of(100756, 50346)
        )
    }
}
