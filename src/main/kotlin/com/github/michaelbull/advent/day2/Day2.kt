package com.github.michaelbull.advent.day2

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readIntcode(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day2.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

private val NOUN_RANGE = 0L..99L
private val VERB_RANGE = 0L..99L

private const val OUTPUT_ADDRESS = 0L
private const val NOUN_ADDRESS = 1L
private const val VERB_ADDRESS = 2L

val IntcodeComputer.output: Long
    get() = memory.getValue(OUTPUT_ADDRESS)

var IntcodeComputer.noun: Long
    get() = memory.getValue(NOUN_ADDRESS)
    set(value) {
        require(value in NOUN_RANGE) { "noun must be in range [$NOUN_RANGE], but was $value" }
        set(NOUN_ADDRESS, value)
    }

var IntcodeComputer.verb: Long
    get() = memory.getValue(VERB_ADDRESS)
    set(value) {
        require(value in VERB_RANGE) { "verb must be in range [$VERB_RANGE], but was $value" }
        set(VERB_ADDRESS, value)
    }

suspend fun IntcodeComputer.part1(program: Intcode): Long {
    memory = program
    noun = 12
    verb = 2
    compute()
    return output
}

suspend fun IntcodeComputer.part2(program: Intcode): Long {
    for (candidateNoun in NOUN_RANGE) {
        for (candidateVerb in VERB_RANGE) {
            memory = program
            noun = candidateNoun
            verb = candidateVerb
            compute()

            if (output == 19690720L) {
                return 100 * candidateNoun + candidateVerb
            }
        }
    }

    throw IllegalArgumentException("Could not find noun & verb within $program")
}

fun main() = runBlocking {
    val program = readIntcode()
    val computer = IntcodeComputer()
    println("part 1 = ${computer.part1(program)}")
    println("part 2 = ${computer.part2(program)}")
}
