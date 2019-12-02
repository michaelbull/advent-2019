package com.github.michaelbull.advent.day2

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.IntcodeComputer.Companion.NOUN_RANGE
import com.github.michaelbull.advent.intcode.IntcodeComputer.Companion.VERB_RANGE
import com.github.michaelbull.advent.intcode.toIntcode

fun readIntcode(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day2.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

fun IntcodeComputer.part1(input: Intcode): Int {
    read(input)
    noun = 12
    verb = 2
    compute()
    return output
}

fun IntcodeComputer.part2(input: Intcode): Int {
    for (candidateNoun in NOUN_RANGE) {
        for (candidateVerb in VERB_RANGE) {
            read(input)
            noun = candidateNoun
            verb = candidateVerb
            compute()

            if (output == 19690720) {
                return 100 * candidateNoun + candidateVerb
            }
        }
    }

    throw IllegalArgumentException("Could not find noun & verb within $input")
}

fun main() {
    val input = readIntcode()
    val computer = IntcodeComputer()
    println("part 1 = ${computer.part1(input)}")
    println("part 2 = ${computer.part2(input)}")
}
