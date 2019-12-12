package com.github.michaelbull.advent.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import java.util.stream.Stream.of

class Day12ExamplesTest {

    private val moons = listOf(
        Moon(Position(-1, 0, 2)),
        Moon(Position(2, -10, -7)),
        Moon(Position(4, -8, 8)),
        Moon(Position(3, 5, -1))
    )

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "after {0} steps: {1}")
    fun part1Examples(steps: Int, expectedMoons: List<Moon>) {
        val actualMoons = simulateMotion(moons).elementAt(steps - 1)
        assertEquals(expectedMoons, actualMoons)
    }

    @Test
    fun part1TotalEnergy() {
        val actualEnergy = simulateMotion(moons).elementAt(9).totalEnergy()
        assertEquals(179, actualEnergy)
    }

    @Test
    fun part2Example() {
        assertEquals(2772, moons.part2())
    }

    private data class Part1Arguments(
        val steps: Int,
        val moons: List<Moon>
    ) : Arguments {
        override fun get() = arrayOf(steps, moons)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = of(
            Part1Arguments(
                steps = 1,
                moons = listOf(
                    Moon(Position(2, -1, 1), Velocity(3, -1, -1)),
                    Moon(Position(3, -7, -4), Velocity(1, 3, 3)),
                    Moon(Position(1, -7, 5), Velocity(-3, 1, -3)),
                    Moon(Position(2, 2, 0), Velocity(-1, -3, 1))
                )
            ),
            Part1Arguments(
                steps = 2,
                moons = listOf(
                    Moon(Position(5, -3, -1), Velocity(3, -2, -2)),
                    Moon(Position(1, -2, 2), Velocity(-2, 5, 6)),
                    Moon(Position(1, -4, -1), Velocity(0, 3, -6)),
                    Moon(Position(1, -4, 2), Velocity(-1, -6, 2))
                )
            ),
            Part1Arguments(
                steps = 3,
                moons = listOf(
                    Moon(Position(5, -6, -1), Velocity(0, -3, 0)),
                    Moon(Position(0, 0, 6), Velocity(-1, 2, 4)),
                    Moon(Position(2, 1, -5), Velocity(1, 5, -4)),
                    Moon(Position(1, -8, 2), Velocity(0, -4, 0))
                )
            ),
            Part1Arguments(
                steps = 4,
                moons = listOf(
                    Moon(Position(2, -8, 0), Velocity(-3, -2, 1)),
                    Moon(Position(2, 1, 7), Velocity(2, 1, 1)),
                    Moon(Position(2, 3, -6), Velocity(0, 2, -1)),
                    Moon(Position(2, -9, 1), Velocity(1, -1, -1))
                )
            ),
            Part1Arguments(
                steps = 5,
                moons = listOf(
                    Moon(Position(-1, -9, 2), Velocity(-3, -1, 2)),
                    Moon(Position(4, 1, 5), Velocity(2, 0, -2)),
                    Moon(Position(2, 2, -4), Velocity(0, -1, 2)),
                    Moon(Position(3, -7, -1), Velocity(1, 2, -2))
                )
            ),
            Part1Arguments(
                steps = 6,
                moons = listOf(
                    Moon(Position(-1, -7, 3), Velocity(0, 2, 1)),
                    Moon(Position(3, 0, 0), Velocity(-1, -1, -5)),
                    Moon(Position(3, -2, 1), Velocity(1, -4, 5)),
                    Moon(Position(3, -4, -2), Velocity(0, 3, -1))
                )
            ),
            Part1Arguments(
                steps = 7,
                moons = listOf(
                    Moon(Position(2, -2, 1), Velocity(3, 5, -2)),
                    Moon(Position(1, -4, -4), Velocity(-2, -4, -4)),
                    Moon(Position(3, -7, 5), Velocity(0, -5, 4)),
                    Moon(Position(2, 0, 0), Velocity(-1, 4, 2))
                )
            ),
            Part1Arguments(
                steps = 8,
                moons = listOf(
                    Moon(Position(5, 2, -2), Velocity(3, 4, -3)),
                    Moon(Position(2, -7, -5), Velocity(1, -3, -1)),
                    Moon(Position(0, -9, 6), Velocity(-3, -2, 1)),
                    Moon(Position(1, 1, 3), Velocity(-1, 1, 3))
                )
            ),
            Part1Arguments(
                steps = 9,
                moons = listOf(
                    Moon(Position(5, 3, -4), Velocity(0, 1, -2)),
                    Moon(Position(2, -9, -3), Velocity(0, -2, 2)),
                    Moon(Position(0, -8, 4), Velocity(0, 1, -2)),
                    Moon(Position(1, 1, 5), Velocity(0, 0, 2))
                )
            ),
            Part1Arguments(
                steps = 10,
                moons = listOf(
                    Moon(Position(2, 1, -3), Velocity(-3, -2, 1)),
                    Moon(Position(1, -8, 0), Velocity(-1, 1, 3)),
                    Moon(Position(3, -6, 1), Velocity(3, 2, -3)),
                    Moon(Position(2, 0, 4), Velocity(1, -1, -1))
                )
            )
        )
    }
}
