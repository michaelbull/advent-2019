package com.github.michaelbull.advent.day11

sealed class Direction {
    object Up : Direction()
    object Down : Direction()
    object Left : Direction()
    object Right : Direction()
}
