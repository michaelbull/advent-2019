package com.github.michaelbull.advent.day11

import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11AnswersTest {

    private val program = readProgram()

    @Test
    fun answer1() = runBlockingTest {
        assertEquals(1894, program.part1())
    }

    @Test
    fun answer2() = runBlockingTest {
        val expected = """
            |   ## #  # #### #    ####   ## ###  #  #   
            |    # # #     # #       #    # #  # #  #   
            |    # ##     #  #      #     # ###  ####   
            |    # # #   #   #     #      # #  # #  #   
            | #  # # #  #    #    #    #  # #  # #  #   
            |  ##  #  # #### #### ####  ##  ###  #  #   
        """.trimMargin()

        assertEquals(expected, program.part2())
    }
}
