package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10AnswersTest {

    private val map = readAsteroidMap()
    private val station = Position(20, 21)
    private val stationDetections = 247

    @Test
    fun answer1() {
        val (actualStation, actualDetections) = map.part1()!!
        assertEquals(station, actualStation)
        assertEquals(stationDetections, actualDetections)
    }

    @Test
    fun answer2() {
        assertEquals(1919, map.part2(station))
    }
}
