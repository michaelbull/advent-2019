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

val IntcodeComputer.diagnosticCode: Int
    get() = outputs.joinToString(separator = "").toInt()

fun IntcodeComputer.runTest(program: Intcode, systemId: Int) {
    memory = program
    inputs = listOf(systemId)
    compute()
}

fun main() {
    val program = readIntcode()
    val computer = IntcodeComputer()

    computer.runTest(program, systemId = 1)
    println("part 1 = ${computer.diagnosticCode}")

    computer.runTest(program, systemId = 5)
    println("part 2 = ${computer.diagnosticCode}")
}
