package com.github.michaelbull.advent.intcode

import kotlin.math.max

class InstructionReader(
    private val memory: Intcode,
    private val instructionPointer: Int = 0
) {

    private lateinit var modes: String

    fun read(): Instruction {
        val value = memory[instructionPointer].toString()
        val opcodeStart = max(value.length - OPCODE_LENGTH, 0)
        val opcode = value.substring(opcodeStart, value.length).toInt().toOpcode()
        modes = value.dropLast(OPCODE_LENGTH).reversed()

        return when (opcode) {
            Opcode.Halt -> Instruction.Halt

            Opcode.Add -> Instruction.Add(
                left = memory.parameter(1),
                right = memory.parameter(2),
                targetAddress = memory.address(3)
            )

            Opcode.Multiply -> Instruction.Multiply(
                left = memory.parameter(1),
                right = memory.parameter(2),
                targetAddress = memory.address(3)
            )

            Opcode.Input -> Instruction.Input(
                targetAddress = memory.address(1)
            )

            Opcode.Output -> Instruction.Output(
                value = memory.parameter(1)
            )

            Opcode.JumpIfTrue -> Instruction.JumpIfTrue(
                value = memory.parameter(1),
                pointer = memory.parameter(2)
            )

            Opcode.JumpIfFalse -> Instruction.JumpIfFalse(
                value = memory.parameter(1),
                pointer = memory.parameter(2)
            )

            Opcode.LessThan -> Instruction.LessThan(
                left = memory.parameter(1),
                right = memory.parameter(2),
                targetAddress = memory.address(3)
            )

            Opcode.Equals -> Instruction.Equals(
                left = memory.parameter(1),
                right = memory.parameter(2),
                targetAddress = memory.address(3)
            )
        }
    }

    private fun Int.toOpcode(): Opcode {
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

    private fun parameterMode(number: Int): ParameterMode {
        return modes.getOrNull(number - 1)
            ?.let(Character::getNumericValue)
            ?.toParameterMode()
            ?: ParameterMode.Position
    }

    private fun Intcode.parameter(number: Int): Int {
        val address = instructionPointer + number

        return when (parameterMode(number)) {
            ParameterMode.Position -> this[this[address]]
            ParameterMode.Immediate -> this[address]
        }
    }

    private fun Intcode.address(number: Int): Int {
        return this[instructionPointer + number]
    }

    private companion object {
        private const val OPCODE_LENGTH = 2
    }
}
