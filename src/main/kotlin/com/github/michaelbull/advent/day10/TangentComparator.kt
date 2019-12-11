package com.github.michaelbull.advent.day10

/* https://www.reddit.com/r/adventofcode/comments/e8r1jx/day_10_part_2_discrete_anglecomparing_function_ie/ */
object TangentComparator : Comparator<Tangent> {

    private val clockwiseQuadrants = listOf(
        Quadrant.I,
        Quadrant.IV,
        Quadrant.III,
        Quadrant.II
    )

    private fun Quadrant.toInt(): Int {
        return clockwiseQuadrants.indexOf(this)
    }

    override fun compare(a: Tangent, b: Tangent): Int {
        val quadrantA = a.quadrant
        val quadrantB = b.quadrant

        return if (quadrantA == quadrantB) {
            val oppositeA = a.opposite * b.adjacent
            val oppositeB = b.opposite * a.adjacent
            oppositeA.compareTo(oppositeB)
        } else {
            quadrantA.toInt().compareTo(quadrantB.toInt())
        }
    }
}
