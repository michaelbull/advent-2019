package com.github.michaelbull.advent.day10

import kotlin.math.abs

data class Tangent(
    val adjacent: Int,
    val opposite: Int
) {

    val quadrant: Quadrant
        get() = when {
            adjacent >= 0 && opposite >= 0 -> Quadrant.I
            adjacent < 0 && opposite >= 0 -> Quadrant.II
            adjacent < 0 && opposite < 0 -> Quadrant.III
            adjacent >= 0 && opposite < 0 -> Quadrant.IV
            else -> error("$this is not in a quadrant")
        }

    operator fun div(divisor: Int): Tangent {
        return copy(
            adjacent = this.adjacent / divisor,
            opposite = this.opposite / divisor
        )
    }

    fun simplify(): Tangent {
        return this / greatestCommonDivisor(opposite, adjacent)
    }

    private tailrec fun greatestCommonDivisor(a: Int, b: Int): Int {
        return if (a == 0) {
            abs(b)
        } else {
            greatestCommonDivisor(b % a, a)
        }
    }
}
