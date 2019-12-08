package com.github.michaelbull.advent.day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8AnswersTest {

    private val image = readImage()

    @Test
    fun answer1() {
        assertEquals(1820, image.part1())
    }

    @Test
    fun answer2() {
        val expected = """
            #### #  # #  #  ##    ## 
               # #  # # #  #  #    # 
              #  #  # ##   #       # 
             #   #  # # #  #       # 
            #    #  # # #  #  # #  # 
            ####  ##  #  #  ##   ##  
        """.trimIndent()

        assertEquals(expected, image.part2())
    }
}
