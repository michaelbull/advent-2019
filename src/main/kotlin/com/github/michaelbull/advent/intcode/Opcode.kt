package com.github.michaelbull.advent.intcode

sealed class Opcode(val parameters: Int = 0) {
    object Add : Opcode(parameters = 3)
    object Multiply : Opcode(parameters = 3)
    object Halt : Opcode(parameters = 0)
    object Input : Opcode(parameters = 1)
    object Output : Opcode(parameters = 1)
    object JumpIfTrue : Opcode(parameters = 2)
    object JumpIfFalse : Opcode(parameters = 2)
    object LessThan : Opcode(parameters = 3)
    object Equals : Opcode(parameters = 3)
}

fun Int.toOpcode(): Opcode {
    return when (this) {
        1 -> Opcode.Add
        2 -> Opcode.Multiply
        3 -> Opcode.Input
        4 -> Opcode.Output
        5 -> Opcode.JumpIfTrue
        6 -> Opcode.JumpIfFalse
        7 -> Opcode.LessThan
        8 -> Opcode.Equals
        99 -> Opcode.Halt
        else -> error("Unknown opcode $this")
    }
}
