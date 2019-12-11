package com.github.michaelbull.advent.day11

sealed class TurnInstruction {
    object Left : TurnInstruction()
    object Right : TurnInstruction()
}

fun Long.toTurnInstruction(): TurnInstruction {
    return when (this) {
        0L -> TurnInstruction.Left
        1L -> TurnInstruction.Right
        else -> error("Unknown instruction $this")
    }
}
