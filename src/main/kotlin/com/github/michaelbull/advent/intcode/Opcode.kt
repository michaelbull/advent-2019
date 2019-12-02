package com.github.michaelbull.advent.intcode

sealed class Opcode {
    object Add : Opcode()
    object Multiply : Opcode()
    object Halt : Opcode()
}

fun Int.toOpcode(): Opcode {
    return when (this) {
        1 -> Opcode.Add
        2 -> Opcode.Multiply
        99 -> Opcode.Halt
        else -> error("Unknown opcode $this")
    }
}
