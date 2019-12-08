package com.github.michaelbull.advent.day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8ExamplesTest {

    @Test
    fun part1Example() {
        val layer1 = Layer(
            listOf(1, 2, 3),
            listOf(4, 5, 6)
        )

        val layer2 = Layer(
            listOf(7, 8, 9),
            listOf(0, 1, 2)
        )

        val expected = Image(width = 3, height = 2, layers = listOf(layer1, layer2))
        val actual = "123456789012".toImage(width = 3, height = 2)
        assertEquals(expected, actual)
    }

    @Test
    fun part2Example() {
        val image = "0222112222120000".toImage(width = 2, height = 2)
        val actual = image.decode()
        val expected = Layer(listOf(listOf(0, 1), listOf(1, 0)))
        assertEquals(expected, actual)
    }
}
