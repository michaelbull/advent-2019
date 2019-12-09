package com.github.michaelbull.advent.intcode

class IntcodeComputer {

    private var instructionPointer = 0L
    private var relativeBase = 0L
    private var inputPointer = 0
    private var modifiedInstructionPointer = false
    private var _memory = mutableMapOf<Long, Long>().withDefault { 0 }

    var memory: Map<Long, Long>
        get() = _memory
        set(value) {
            _memory = value.toMutableMap().withDefault { 0 }
        }

    private lateinit var onInput: suspend (Int) -> Long
    private lateinit var onOutput: suspend (Long) -> Unit

    fun onInput(action: suspend (Int) -> Long) {
        this.onInput = action
    }

    fun onOutput(action: suspend (Long) -> Unit) {
        this.onOutput = action
    }

    suspend fun compute() {
        reset()

        while (true) {
            modifiedInstructionPointer = false

            val reader = InstructionReader(_memory, instructionPointer, relativeBase)
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

    operator fun set(address: Long, value: Long) {
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
            is Instruction.JumpIfTrue -> jumpIf(pointer) { value != 0L }
            is Instruction.JumpIfFalse -> jumpIf(pointer) { value == 0L }
            is Instruction.LessThan -> setIf(targetAddress) { left < right }
            is Instruction.Equals -> setIf(targetAddress) { left == right }
            is Instruction.RelativeBaseOffset -> relativeBase += offset
        }
    }

    private inline fun setIf(address: Long, predicate: () -> Boolean) {
        set(address, if (predicate()) 1 else 0)
    }

    private inline fun jumpIf(pointer: Long, predicate: () -> Boolean) {
        if (predicate()) {
            instructionPointer = pointer
            modifiedInstructionPointer = true
        }
    }

    override fun toString(): String {
        return "IntcodeComputer(memory=$_memory)"
    }
}
