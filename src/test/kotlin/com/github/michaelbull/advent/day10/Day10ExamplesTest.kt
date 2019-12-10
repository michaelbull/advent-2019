package com.github.michaelbull.advent.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day10ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "best is {0} with {1} other asteroids detected in {2}")
    fun part1Examples(bestPosition: Position, bestDetections: Int, map: AsteroidMap) {
        assertEquals(Pair(bestPosition, bestDetections), map.part1())
    }

    private data class ExampleArguments(
        val bestPosition: Position,
        val bestDetections: Int,
        val map: String
    ) : Arguments {
        override fun get() = arrayOf(bestPosition, bestDetections, map.trimIndent().toAsteroidMap())
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            ExampleArguments(
                bestPosition = Position(3, 4),
                bestDetections = 8,
                map = """
                    .#..#
                    .....
                    #####
                    ....#
                    ...##
                """
            ),
            ExampleArguments(
                bestPosition = Position(5, 8),
                bestDetections = 33,
                map = """
                    ......#.#.
                    #..#.#....
                    ..#######.
                    .#.#.###..
                    .#..#.....
                    ..#....#.#
                    #..#....#.
                    .##.#..###
                    ##...#..#.
                    .#....####
                """
            ),
            ExampleArguments(
                bestPosition = Position(1, 2),
                bestDetections = 35,
                map = """
                    #.#...#.#.
                    .###....#.
                    .#....#...
                    ##.#.#.#.#
                    ....#.#.#.
                    .##..###.#
                    ..#...##..
                    ..##....##
                    ......#...
                    .####.###.
                """
            ),
            ExampleArguments(
                bestPosition = Position(6, 3),
                bestDetections = 41,
                map = """
                    .#..#..###
                    ####.###.#
                    ....###.#.
                    ..###.##.#
                    ##.##.#.#.
                    ....###..#
                    ..#.#..#.#
                    #..#.#.###
                    .##...##.#
                    .....#.#..
                """
            ),
            ExampleArguments(
                bestPosition = Position(11, 13),
                bestDetections = 210,
                map = """
                    .#..##.###...#######
                    ##.############..##.
                    .#.######.########.#
                    .###.#######.####.#.
                    #####.##.#.##.###.##
                    ..#####..#.#########
                    ####################
                    #.####....###.#.#.##
                    ##.#################
                    #####.##.###..####..
                    ..######..##.#######
                    ####.##.####...##..#
                    .#####..#.######.###
                    ##...#.##########...
                    #.##########.#######
                    .####.#.###.###.#.##
                    ....##.##.###..#####
                    .#.#.###########.###
                    #.#.#.#####.####.###
                    ###.##.####.##.#..##
                """
            )
        )
    }
}
