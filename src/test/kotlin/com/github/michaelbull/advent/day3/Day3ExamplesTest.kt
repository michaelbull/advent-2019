package com.github.michaelbull.advent.day3

import com.github.michaelbull.advent.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day3ExamplesTest {

    @ArgumentsSource(Part1GridExamples::class)
    @ParameterizedTest(name = "{0}")
    fun part1GridExamples(paths: List<String>, expected: String) {
        val centre = Position(1, 1)
        val grid = Grid(centre)
        paths.toWires().forEach(grid::plot)

        assertEquals(expected.trimIndent(), grid.prettyPrint(width = 11, height = 10))
    }

    @ArgumentsSource(Part1DistanceExamples::class)
    @ParameterizedTest(name = "[{0}, {1}] = distance {2}")
    fun part1DistanceExamples(first: String, second: String, expectedDistance: Int) {
        val grid = Grid()
        val wires = listOf(first.toWire(1), second.toWire(2))
        wires.forEach(grid::plot)

        assertEquals(expectedDistance, grid.part1())
    }

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "[{0}, {1}] = {2} steps")
    fun part2Examples(first: String, second: String, expectedSteps: Int) {
        val grid = Grid()
        val wires = listOf(first.toWire(1), second.toWire(2))
        wires.forEach(grid::plot)

        assertEquals(expectedSteps, grid.part2(wires))
    }

    private class Part1GridExamples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf("R8,U5,L5,D3"),
                """
                    ...........
                    ...........
                    ...........
                    ....+----+.
                    ....|....|.
                    ....|....|.
                    ....|....|.
                    .........|.
                    .o-------+.
                    ...........
                """
            ),
            Arguments.of(
                listOf("R8,U5,L5,D3", "U7,R6,D4,L4"),
                """
                    ...........
                    .+-----+...
                    .|.....|...
                    .|..+--X-+.
                    .|..|..|.|.
                    .|.-X--+.|.
                    .|..|....|.
                    .|.......|.
                    .o-------+.
                    ...........
                """
            )
        )
    }

    private class Part1DistanceExamples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 159),
            Arguments.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", 135)
        )
    }

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Arguments.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", 610),
            Arguments.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", 410)
        )
    }
}
