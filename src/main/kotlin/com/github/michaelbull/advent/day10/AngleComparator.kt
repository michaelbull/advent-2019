package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position
import kotlin.math.sign

/* https://www.reddit.com/r/adventofcode/comments/e8r1jx/day_10_part_2_discrete_anglecomparing_function_ie/ */
object AngleComparator : Comparator<Position> {
    override fun compare(a: Position, b: Position): Int {
        val aPlane = a.x < 0
        val bPlane = b.x < 0

        return when {
            aPlane != bPlane -> aPlane.compareTo(bPlane)
            a.x == 0 && b.x == 0 -> a.y.sign.compareTo(b.y.sign)
            else -> (b cross a).sign
        }
    }
}
