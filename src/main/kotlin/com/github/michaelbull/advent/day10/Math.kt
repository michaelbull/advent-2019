package com.github.michaelbull.advent.day10

import kotlin.math.abs

tailrec fun greatestCommonDivisor(a: Int, b: Int): Int {
    return if (a == 0) {
        abs(b)
    } else {
        greatestCommonDivisor(b % a, a)
    }
}
