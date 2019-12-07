package com.github.michaelbull.advent.intcode

class IntcodeComputer {

    private var instructionPointer = 0
    private var inputPointer = 0
    private var modifiedInstructionPointer = false
    private lateinit var _memory: MutableList<Int>

    var memory: Intcode
        get() = _memory.toList()
        set(value) {
            _memory = value.toMutableList()
        }

    val isFinished: Boolean
        get() {
            return instructionPointer == memory.lastIndex
        }

    private lateinit var onInput: (Int) -> Int
    private lateinit var onOutput: (Int) -> Unit

    fun onInput(action: (Int) -> Int) {
        this.onInput = action
    }

    fun onOutput(action: (Int) -> Unit) {
        this.onOutput = action
    }

    fun reset() {
        instructionPointer = 0
        inputPointer = 0
    }

    fun compute() {
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

    operator fun set(address: Int, value: Int) {
        _memory[address] = value
    }

    private fun Instruction.run() {
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
}
