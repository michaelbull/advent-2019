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

    return coroutineScope {
        for (i in 0 until amplifiers.size - 1) {
            val curr = amplifiers[i]
            val next = amplifiers[i + 1]
            val channel = computers.channelBetween(curr, next)
            addInputs(channel, next)
        }

        val first = amplifiers.first()
        val last = amplifiers.last()
        val feedbackChannel = computers.channelBetween(last, first, capacity = 1)
        addInputs(feedbackChannel, first)

        for (computer in computers.values) {
            compute(computer, this@thrusterSignalFeedbackLoop)
        }

        feedbackChannel
    }.receive()
}

private fun CoroutineScope.addInputs(channel: Channel<Int>, amplifier: Amplifier) = launch {
    /* Provide each amplifier its phase setting at its first input instruction */
    channel.send(amplifier.phaseSetting)

    /* To start the process, a 0 signal is sent to amplifier A's input exactly once. */
    if (amplifier.id == 'A') {
        channel.send(0)
    }
}

private fun CoroutineScope.compute(computer: IntcodeComputer, program: Intcode) = launch {
    computer.reset()
    computer.memory = program
    computer.compute()
}

private suspend fun Map<Amplifier, IntcodeComputer>.channelBetween(
    curr: Amplifier,
    next: Amplifier,
    capacity: Int = Channel.RENDEZVOUS
) = Channel<Int>(capacity).apply {
    getValue(curr).onOutput { send(it) }
    getValue(next).onInput { receive() }
}

fun main() = runBlocking {
    val software = readSoftware()
    println("part 1 = ${software.part1()}")
    println("part 2 = ${software.part2()}")
}
