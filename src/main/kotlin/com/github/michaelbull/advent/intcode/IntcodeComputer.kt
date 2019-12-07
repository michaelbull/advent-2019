package com.github.michaelbull.advent.intcode

import kotlinx.coroutines.runBlocking

class IntcodeComputer {

    private var instructionPointer = 0
    private var inputPointer = 0
    private var modifiedInstructionPointer = false
    private var _memory = mutableListOf<Int>()

    var memory: Intcode
        get() = _memory.toList()
        set(value) {
            _memory = value.toMutableList()
        }

    private lateinit var onInput: suspend (Int) -> Int
    private lateinit var onOutput: suspend (Int) -> Unit

    fun onInput(action: suspend (Int) -> Int) {
        this.onInput = action
    }

    fun onOutput(action: suspend (Int) -> Unit) {
        this.onOutput = action
    }

    suspend fun compute() {
        reset()

        while (true) {
            modifiedInstructionPointer = false

            val reader = InstructionReader(_memory, instructionPointer)
            val instruction = reader.read()

            if (instruction == Instruction.Halt) {
                break
            } else {
                instruction.run()

                if (!modifiedInstructionPointer) {
                    instructionPointer += instruction.parameters + 1
                }
            }
        }
    }

    fun computeBlocking() = runBlocking { compute() }

    operator fun set(address: Int, value: Int) {
        _memory[address] = value
    }

    private fun reset() {
        instructionPointer = 0
        inputPointer = 0
    }

    private suspend fun Instruction.run() {
        return when (this) {
            is Instruction.Halt -> error("Cannot run $this")
            is Instruction.Add -> set(targetAddress, left + right)
            is Instruction.Multiply -> set(targetAddress, left * right)
            is Instruction.Input -> set(targetAddress, onInput(inputPointer++))
            is Instruction.Output -> onOutput(value)
            is Instruction.JumpIfTrue -> jumpIf(pointer) { value != 0 }
            is Instruction.JumpIfFalse -> jumpIf(pointer) { value == 0 }
            is Instruction.LessThan -> setIf(targetAddress) { left < right }
            is Instruction.Equals -> setIf(targetAddress) { left == right }
        }
    }

    private inline fun setIf(address: Int, predicate: () -> Boolean) {
        set(address, if (predicate()) 1 else 0)
    }

    private inline fun jumpIf(pointer: Int, predicate: () -> Boolean) {
        if (predicate()) {
            instructionPointer = pointer
            modifiedInstructionPointer = true
        }
    }

    override fun toString(): String {
        return "IntcodeComputer(memory=$_memory)"
    }
}
