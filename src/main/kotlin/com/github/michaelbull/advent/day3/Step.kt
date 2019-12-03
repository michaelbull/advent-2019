package com.github.michaelbull.advent.day3

data class Step(
    val direction: Direction,
    val length: Int
)

fun String.toStep(): Step {
    val direction = first().toDirection()
    val length = substring(1).toInt()
    return Step(direction, length)
}
