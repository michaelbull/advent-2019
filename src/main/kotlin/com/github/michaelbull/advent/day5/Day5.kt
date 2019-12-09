package com.github.michaelbull.advent.day5

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readIntcode(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day5.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

suspend fun IntcodeComputer.runTest(program: Intcode, systemId: Long) {
    memory = program
    onInput { if (it == 0) systemId else error("No input at $it") }
    compute()
}

fun main() = runBlocking {
    val program = readIntcode()
    val computer = IntcodeComputer()

    var diagnosticCode = 0L
    computer.onOutput { diagnosticCode = it }

    computer.runTest(program, systemId = 1)
    println("part 1 = $diagnosticCode")

    computer.runTest(program, systemId = 5)
    println("part 2 = $diagnosticCode")
}
