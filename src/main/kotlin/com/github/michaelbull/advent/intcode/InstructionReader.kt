package com.github.michaelbull.advent.intcode

import kotlin.math.max

class InstructionReader(
    private val memory: Intcode,
    private val instructionPointer: Int = 0
) {

    private lateinit var modes: String

    fun read(): Instruction {
        val first = memory[instructionPointer].toString()
        val opcodeString = first.substring(max(first.length - 2, 0), first.length)
        val opcode = opcodeString.toInt().toOpcode()
        modes = first.substring(0, first.length - opcodeString.length).reversed()

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
}
