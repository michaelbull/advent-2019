package com.github.michaelbull.advent.intcode

sealed class Instruction(val parameters: Int = 0) {

    object Halt : Instruction(parameters = 0) {
        override fun toString() = "Halt"
    }

    data class Add(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(parameters = 3)

    data class Multiply(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(parameters = 3)

    data class Input(
        val targetAddress: Int
    ) : Instruction(parameters = 1)

    data class Output(
        val value: Int
    ) : Instruction(parameters = 1)

    data class JumpIfTrue(
        val value: Int,
        val pointer: Int
    ) : Instruction(parameters = 2)

    data class JumpIfFalse(
        val value: Int,
        val pointer: Int
    ) : Instruction(parameters = 2)

    data class LessThan(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(parameters = 3)

    data class Equals(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(parameters = 3)
}
