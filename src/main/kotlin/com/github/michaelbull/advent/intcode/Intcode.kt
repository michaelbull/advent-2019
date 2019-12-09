package com.github.michaelbull.advent.intcode

typealias Intcode = Map<Long, Long>

fun String.toIntcode(): Intcode {
    return split(",")
        .map(String::toLong)
        .withIndex()
        .associateBy({ it.index.toLong() }, { it.value })
}
