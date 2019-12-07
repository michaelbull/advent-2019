package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode

fun readSoftware(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day7.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

fun Intcode.part1(): Int {
    return highestThrusterSignal(
        phaseSettings = intArrayOf(0, 1, 2, 3, 4),
        getSignal = { thrusterSignal(it) }
    )
}

inline fun Intcode.highestThrusterSignal(
    phaseSettings: IntArray,
    crossinline getSignal: IntcodeComputer.(IntArray) -> Int
): Int {
    var highest = -1

    phaseSettings.forEachPermutation { permutation ->
        val computer = IntcodeComputer()
        computer.memory = this

        val signal = computer.getSignal(permutation)

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

fun IntcodeComputer.thrusterSignal(phaseSettings: IntArray): Int {
    var input = 0

    for (phaseSetting in phaseSettings) {
        onInput { index ->
            when (index) {
                0 -> phaseSetting
                1 -> input
                else -> error("No input at $index")
            }
        }

        onOutput { signal ->
            input = signal
        }

        reset()
        compute()
    }

    return input
}

fun main() {
    val software = readSoftware()
    println("part 1 = ${software.part1()}")
}
