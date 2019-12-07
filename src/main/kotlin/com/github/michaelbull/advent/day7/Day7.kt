package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun readSoftware(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day7.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

suspend fun Intcode.part1(): Int {
    return highestThrusterSignal(
        phaseSettings = intArrayOf(0, 1, 2, 3, 4),
        getSignal = { thrusterSignal(it) }
    )
}

suspend fun Intcode.part2(): Int {
    return highestThrusterSignal(
        phaseSettings = intArrayOf(5, 6, 7, 8, 9),
        getSignal = { thrusterSignalFeedbackLoop(it) }
    )
}

suspend fun Intcode.highestThrusterSignal(
    phaseSettings: IntArray,
    getSignal: suspend Intcode.(IntArray) -> Int
): Int {
    var highest = -1

    phaseSettings.forEachPermutation { permutation ->
        val signal = getSignal(permutation)

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

fun Intcode.thrusterSignal(phaseSettings: IntArray): Int {
    val amplifiers = phaseSettings.toAmplifiers()
    val computer = IntcodeComputer()

    var input = 0

    for (amplifier in amplifiers) {
        computer.onInput { index ->
            when (index) {
                0 -> amplifier.phaseSetting
                1 -> input
                else -> error("No input at $index")
            }
        }

        computer.onOutput { signal ->
            input = signal
        }

        computer.reset()
        computer.memory = this
        computer.computeBlocking()
    }

    return input
}

suspend fun Intcode.thrusterSignalFeedbackLoop(phaseSettings: IntArray): Int {
    val amplifiers = phaseSettings.toAmplifiers()
    val computers = amplifiers.associateWith { IntcodeComputer() }

    for (i in 0 until amplifiers.size - 1) {
        val curr = amplifiers[i]
        val next = amplifiers[i + 1]

        computers.pair(curr, next)
    }

    val loopChannel = computers.pair(
        curr = amplifiers.last(),
        next = amplifiers.first()
    )

    coroutineScope {
        for (computer in computers.values) {
            compute(computer, this@thrusterSignalFeedbackLoop)
        }
    }

    return loopChannel.receive()
}

private fun CoroutineScope.compute(computer: IntcodeComputer, program: Intcode) = launch {
    computer.reset()
    computer.memory = program
    computer.compute()
}

private suspend fun Map<Amplifier, IntcodeComputer>.pair(curr: Amplifier, next: Amplifier): Channel<Int> {

    /* All signals sent or received in this process will be between pairs */
    val channel = Channel<Int>(capacity = 2)

    /* Provide each amplifier its phase setting at its first input instruction */
    channel.send(next.phaseSetting)

    /* To start the process, a 0 signal is sent to amplifier A's input exactly once. */
    if (next.id == 'A') {
        channel.send(0)
    }

    getValue(curr).onOutput {
        channel.send(it)
    }

    getValue(next).onInput {
        channel.receive()
    }

    return channel
}

fun main() = runBlocking {
    val software = readSoftware()
    println("part 1 = ${software.part1()}")
    println("part 2 = ${software.part2()}")
}
