package com.github.michaelbull.advent.day10

import kotlin.math.sign

/* https://www.reddit.com/r/adventofcode/comments/e8r1jx/day_10_part_2_discrete_anglecomparing_function_ie/ */
object TangentComparator : Comparator<Tangent> {

    private val leftQuadrants = setOf(Quadrant.II, Quadrant.III)

    private infix fun Tangent.cross(other: Tangent): Long {
        return (adjacent.toLong() * other.opposite) - (opposite.toLong() * other.adjacent)
    }

    override fun compare(a: Tangent, b: Tangent): Int {
        val aLeft = a.quadrant in leftQuadrants
        val bLeft = b.quadrant in leftQuadrants

        return when {
            aLeft != bLeft -> aLeft.compareTo(bLeft)
            a.adjacent == 0 && b.adjacent == 0 -> a.opposite.sign.compareTo(b.opposite.sign)
            else -> (b cross a).sign
        }
    }
}
