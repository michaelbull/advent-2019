package com.github.michaelbull.advent.day3

sealed class Cell {
    object Empty : Cell()
    object CentralPort : Cell()
    object Intersection : Cell()
    data class WireHorizontal(val wire: Wire) : Cell()
    data class WireVertical(val wire: Wire) : Cell()
    data class WireCorner(val wire: Wire) : Cell()
}
