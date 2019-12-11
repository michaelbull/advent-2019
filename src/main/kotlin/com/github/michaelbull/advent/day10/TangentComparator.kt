package com.github.michaelbull.advent.day10

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
