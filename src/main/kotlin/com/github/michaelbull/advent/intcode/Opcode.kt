package com.github.michaelbull.advent.intcode

sealed class Opcode {
    object Add : Opcode()
    object Multiply : Opcode()
    object Halt : Opcode()
    object Input : Opcode()
    object Output : Opcode()
    object JumpIfTrue : Opcode()
    object JumpIfFalse : Opcode()
    object LessThan : Opcode()
    object Equals : Opcode()
}
