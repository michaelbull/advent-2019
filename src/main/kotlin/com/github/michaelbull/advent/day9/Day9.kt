package com.github.michaelbull.advent.day9

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readBoost(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day9.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

suspend fun Intcode.part1(): Long {
    return runBoost(input = 1)
}

suspend fun Intcode.part2(): Long {
    return runBoost(input = 2)
}

private suspend fun Intcode.runBoost(input: Long): Long {
    var output = 0L
    val computer = IntcodeComputer()
    computer.memory = this
    computer.onInput { if (it == 0) input else error("No input at $it") }
    computer.onOutput { output = it }
    computer.compute()
    return output
}

fun main() = runBlocking {
    val boost = readBoost()
    println("part 1 = ${boost.part1()}")
    println("part 2 = ${boost.part2()}")
}
