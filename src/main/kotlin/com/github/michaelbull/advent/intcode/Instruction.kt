package com.github.michaelbull.advent.intcode

sealed class Instruction(
    val size: Int
) {

    object Halt : Instruction(size = 1) {
        override fun toString() = "Halt"
    }

    data class Add(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(size = 4)

    data class Multiply(
        val left: Int,
        val right: Int,
        val targetAddress: Int
    ) : Instruction(size = 4)
}
