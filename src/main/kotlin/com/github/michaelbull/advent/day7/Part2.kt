package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

suspend fun Intcode.part2(): Int {
    return highestThrusterSignal(
        phaseSettings = 5..9,
        getSignal = { feedbackLoopThrusterSignal(it) }
    )
}

suspend fun Intcode.feedbackLoopThrusterSignal(amplifiers: List<Amplifier>): Int {
    val computers = amplifiers.associateWith { IntcodeComputer() }

    return kotlinx.coroutines.coroutineScope {
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
            compute(computer, this@feedbackLoopThrusterSignal)
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
