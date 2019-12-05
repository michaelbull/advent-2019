package com.github.michaelbull.advent.intcode

sealed class Instruction(val opcode: Opcode) {

    object Halt : Instruction(Opcode.Halt) {
        override fun toString() = "Halt"
    }

    data class Add(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(Opcode.Add)

    data class Multiply(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(Opcode.Multiply)

    data class Input(
        val targetAddress: Int
    ) : Instruction(Opcode.Input)

    data class Output(
        val value: Int
    ) : Instruction(Opcode.Output)

    data class JumpIfTrue(
        val value: Int,
        val pointer: Int
    ) : Instruction(Opcode.JumpIfTrue)

    data class JumpIfFalse(
        val value: Int,
        val pointer: Int
    ) : Instruction(Opcode.JumpIfFalse)

    data class LessThan(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(Opcode.LessThan)

    data class Equals(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(Opcode.Equals)
}
