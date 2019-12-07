package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readSoftware(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day7.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

suspend fun Intcode.highestThrusterSignal(
    phaseSettings: IntRange,
    getSignal: suspend Intcode.(List<Amplifier>) -> Int
): Int {
    var highest = -1

    phaseSettings.toList().forEachPermutation { permutation ->
        val amplifiers = permutation.toAmplifiers()
        val signal = getSignal(amplifiers)

        if (highest == -1 || signal > highest) {
            highest = signal
        }
    }

    if (highest == -1) {
        throw IllegalArgumentException("No output signal found")
    } else {
        return highest
    }
}

fun main() = runBlocking {
    val software = readSoftware()
    println("part 1 = ${software.part1()}")
    println("part 2 = ${software.part2()}")
}
