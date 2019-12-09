package com.github.michaelbull.advent.intcode

sealed class Instruction(val parameters: Int = 0) {

    object Halt : Instruction(parameters = 0) {
        override fun toString() = "Halt"
    }

    data class Add(
        val left: Long,
        val right: Long,
        val targetAddress: Long
    ) : Instruction(parameters = 3)

    data class Multiply(
        val left: Long,
        val right: Long,
        val targetAddress: Long
    ) : Instruction(parameters = 3)

    data class Input(
        val targetAddress: Long
    ) : Instruction(parameters = 1)

    data class Output(
        val value: Long
    ) : Instruction(parameters = 1)

    data class JumpIfTrue(
        val value: Long,
        val pointer: Long
    ) : Instruction(parameters = 2)

    data class JumpIfFalse(
        val value: Long,
        val pointer: Long
    ) : Instruction(parameters = 2)

    data class LessThan(
        val left: Long,
        val right: Long,
        val targetAddress: Long
    ) : Instruction(parameters = 3)

    data class Equals(
        val left: Long,
        val right: Long,
        val targetAddress: Long
    ) : Instruction(parameters = 3)

    data class RelativeBaseOffset(
        val offset: Long
    ) : Instruction(parameters = 1)
}
