package com.github.michaelbull.advent.day5

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode

fun readIntcode(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day5.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

fun IntcodeComputer.runTest(program: Intcode, systemId: Int) {
    memory = program
    onInput { if (it == 0) systemId else error("No input at $it") }
    computeBlocking()
}

fun main() {
    val program = readIntcode()
    val computer = IntcodeComputer()

    var diagnosticCode = 0
    computer.onOutput { diagnosticCode = it }

    computer.runTest(program, systemId = 1)
    println("part 1 = $diagnosticCode")

    computer.runTest(program, systemId = 5)
    println("part 2 = $diagnosticCode")
}
