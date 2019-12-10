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

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "vaporization {0} = {1}")
    fun part2Examples(count: Int, expectedPosition: Position) {
        val station = Position(8, 3)

        val map = """
            .#....#####...#..
            ##...##.#####..##
            ##...#...#.#####.
            ..#.........###..
            ..#.#.....#....##
        """.trimIndent().toAsteroidMap()

        val actualPosition = map.vaporizeFrom(station, count)
        assertEquals(expectedPosition, actualPosition)
    }

    private data class Part1Arguments(
        val bestPosition: Position,
        val bestDetections: Int,
        val map: String
    ) : Arguments {
        override fun get() = arrayOf(bestPosition, bestDetections, map.trimIndent().toAsteroidMap())
    }

    private data class Part2Arguments(
        val count: Int,
        val position: Position
    ) : Arguments {
        override fun get() = arrayOf(count, position)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Part1Arguments(
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
            Part1Arguments(
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
            Part1Arguments(
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
            Part1Arguments(
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
            Part1Arguments(
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

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
            Part2Arguments(1, Position(8, 1)),
            Part2Arguments(2, Position(9, 0)),
            Part2Arguments(3, Position(9, 1)),
            Part2Arguments(4, Position(10, 0)),
            Part2Arguments(5, Position(9, 2)),
            Part2Arguments(6, Position(11, 1)),
            Part2Arguments(7, Position(12, 1)),
            Part2Arguments(8, Position(11, 2)),
            Part2Arguments(9, Position(15, 1)),
            Part2Arguments(10, Position(12, 2)),
            Part2Arguments(11, Position(13, 2)),
            Part2Arguments(12, Position(14, 2)),
            Part2Arguments(13, Position(15, 2)),
            Part2Arguments(14, Position(12, 3)),
            Part2Arguments(15, Position(16, 4)),
            Part2Arguments(16, Position(15, 4)),
            Part2Arguments(17, Position(10, 4)),
            Part2Arguments(18, Position(4, 4)),
            Part2Arguments(19, Position(2, 4)),
            Part2Arguments(20, Position(2, 3)),
            Part2Arguments(21, Position(0, 2)),
            Part2Arguments(22, Position(1, 2)),
            Part2Arguments(23, Position(0, 1)),
            Part2Arguments(24, Position(1, 1)),
            Part2Arguments(25, Position(5, 2)),
            Part2Arguments(26, Position(1, 0)),
            Part2Arguments(27, Position(5, 1)),
            Part2Arguments(28, Position(6, 1)),
            Part2Arguments(29, Position(6, 0)),
            Part2Arguments(30, Position(7, 0)),
            Part2Arguments(31, Position(8, 0)),
            Part2Arguments(32, Position(10, 1)),
            Part2Arguments(33, Position(14, 0)),
            Part2Arguments(34, Position(16, 1)),
            Part2Arguments(35, Position(13, 3)),
            Part2Arguments(36, Position(14, 3))
        )
    }
}
