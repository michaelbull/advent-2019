package com.github.michaelbull.advent.day2

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode

fun readIntcode(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day2.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

private val NOUN_RANGE = 0..99
private val VERB_RANGE = 0..99

private const val OUTPUT_ADDRESS = 0
private const val NOUN_ADDRESS = 1
private const val VERB_ADDRESS = 2

val IntcodeComputer.output: Int
    get() = memory[OUTPUT_ADDRESS]

var IntcodeComputer.noun: Int
    get() = memory[NOUN_ADDRESS]
    set(value) {
        require(value in NOUN_RANGE) { "noun must be in range [$NOUN_RANGE], but was $value" }
        set(NOUN_ADDRESS, value)
    }

var IntcodeComputer.verb: Int
    get() = memory[VERB_ADDRESS]
    set(value) {
        require(value in VERB_RANGE) { "verb must be in range [$VERB_RANGE], but was $value" }
        set(VERB_ADDRESS, value)
    }

fun IntcodeComputer.part1(program: Intcode): Int {
    memory = program
    noun = 12
    verb = 2
    computeBlocking()
    return output
}

fun IntcodeComputer.part2(program: Intcode): Int {
    for (candidateNoun in NOUN_RANGE) {
        for (candidateVerb in VERB_RANGE) {
            memory = program
            noun = candidateNoun
            verb = candidateVerb
            computeBlocking()

            if (output == 19690720) {
                return 100 * candidateNoun + candidateVerb
            }
        }
    }

    throw IllegalArgumentException("Could not find noun & verb within $program")
}

fun main() {
    val program = readIntcode()
    val computer = IntcodeComputer()
    println("part 1 = ${computer.part1(program)}")
    println("part 2 = ${computer.part2(program)}")
}
