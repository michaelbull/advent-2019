package com.github.michaelbull.advent.day7

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer

suspend fun Intcode.part1(): Int {
    return highestThrusterSignal(
        phaseSettings = 0..4,
        getSignal = { thrusterSignal(it) }
    )
}

suspend fun Intcode.thrusterSignal(amplifiers: List<Amplifier>): Int {
    val computer = IntcodeComputer()
    var input = 0

    for (amplifier in amplifiers) {
        computer.onInput { index ->
            when (index) {
                0 -> amplifier.phaseSetting
                1 -> input
                else -> kotlin.error("No input at $index")
            }
        }

        computer.onOutput { signal ->
            input = signal
        }

        computer.memory = this
        computer.compute()
    }

    return input
}
