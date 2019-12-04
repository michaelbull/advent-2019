package com.github.michaelbull.advent.day4

fun readRange(): IntRange {
    return ClassLoader.getSystemResourceAsStream("day4.txt")
        .bufferedReader()
        .readLine()
        .split("-")
        .map(String::toInt)
        .let { IntRange(it[0], it[1]) }
}

fun IntRange.validPasswords(criteria: (Int) -> Boolean): Int {
    return filter(criteria).size
}

fun part1(password: Int): Boolean {
    return password.digitsIncreaseLtr() && password.hasTwoAdjacentDigits()
}

fun part2(password: Int): Boolean {
    return password.digitsIncreaseLtr() && password.hasOnlyTwoAdjacentDigits()
}

private fun Int.hasTwoAdjacentDigits(): Boolean {
    val string = toString()

    for (i in 0 until string.length - 1) {
        val currChar = string[i]
        val nextChar = string[i + 1]

        if (currChar == nextChar) {
            return true
        }
    }

    return false
}

private fun Int.digitsIncreaseLtr(): Boolean {
    val string = toString()

    for (i in 0 until string.length - 1) {
        val currDigit = string[i].toInt()
        val nextDigit = string[i + 1].toInt()

        if (nextDigit < currDigit) {
            return false
        }
    }

    return true
}

private fun Int.hasOnlyTwoAdjacentDigits(): Boolean {
    val string = toString()

    for (i in 0 until string.length - 1) {
        val currChar = string[i]
        val nextChar = string[i + 1]

        if (currChar == nextChar) {
            val withinGroup = i > 0 && currChar == string[i - 1]
            val joiningGroup = i < string.length - 2 && currChar == string[i + 2]

            if (!withinGroup && !joiningGroup) {
                return true
            }
        }
    }

    return false
}

fun main() {
    val range = readRange()
    println("part 1 = ${range.validPasswords(::part1)}")
    println("part 2 = ${range.validPasswords(::part2)}")
}
