package com.github.michaelbull.advent.day12

import kotlin.math.abs

tailrec fun greatestCommonDivisor(a: Long, b: Long): Long {
    return if (a == 0L) {
        abs(b)
    } else {
        greatestCommonDivisor(b % a, a)
    }
}

fun leastCommonMultiple(a: Long, b: Long): Long {
    return (a * b) / greatestCommonDivisor(a, b)
}
