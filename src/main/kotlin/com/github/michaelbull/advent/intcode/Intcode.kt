package com.github.michaelbull.advent.intcode

typealias Intcode = List<Int>

fun String.toIntcode(): Intcode {
    return split(",").map(String::toInt)
}
