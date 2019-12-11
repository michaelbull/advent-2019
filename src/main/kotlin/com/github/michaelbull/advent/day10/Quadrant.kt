package com.github.michaelbull.advent.day10

/**
 * https://en.wikipedia.org/wiki/Quadrant_(plane_geometry)
 */
sealed class Quadrant {
    object I : Quadrant() {
        override fun toString() = "Quadrant.I"
    }

    object IV : Quadrant() {
        override fun toString() = "Quadrant.II"
    }

    object III : Quadrant() {
        override fun toString() = "Quadrant.III"
    }

    object II : Quadrant() {
        override fun toString() = "Quadrant.IV"
    }
}
