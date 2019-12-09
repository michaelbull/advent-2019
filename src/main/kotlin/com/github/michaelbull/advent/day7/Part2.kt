package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun Intcode.part2(): Long {
    return highestThrusterSignal(
        phaseSettings = 5..9,
        getSignal = { feedbackLoopThrusterSignal(it) }
    )
}

suspend fun Intcode.feedbackLoopThrusterSignal(amplifiers: List<Amplifier>): Long {
    val computers = amplifiers.associateWith { IntcodeComputer() }

    return coroutineScope {
        for (i in 0 until amplifiers.size - 1) {
            val curr = amplifiers[i]
            val next = amplifiers[i + 1]
            val channel = Channel<Long>()
            computers.pair(channel, curr, next)
            addInputs(channel, next)
        }

        val first = amplifiers.first()
        val last = amplifiers.last()
        val feedbackChannel = Channel<Long>(capacity = 1)
        computers.pair(feedbackChannel, last, first)
        addInputs(feedbackChannel, first)

        for (computer in computers.values) {
            compute(computer, this@feedbackLoopThrusterSignal)
        }

        feedbackChannel
    }.receive()
}

private fun CoroutineScope.addInputs(channel: Channel<Long>, amplifier: Amplifier) = launch {
    /* Provide each amplifier its phase setting at its first input instruction */
    channel.send(amplifier.phaseSetting.toLong())

    /* To start the process, a 0 signal is sent to amplifier A's input exactly once. */
    if (amplifier.id == 'A') {
        channel.send(0)
    }
}

private fun CoroutineScope.compute(computer: IntcodeComputer, program: Intcode) = launch {
    computer.memory = program
    computer.compute()
}

private suspend fun Map<Amplifier, IntcodeComputer>.pair(
    channel: Channel<Long>,
    curr: Amplifier,
    next: Amplifier
) {
    getValue(curr).onOutput { channel.send(it) }
    getValue(next).onInput { channel.receive() }
}
