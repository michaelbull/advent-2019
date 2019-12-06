package com.github.michaelbull.advent.intcode

class IntcodeComputer {

    private var instructionPointer = 0
    private var inputPointer = 0
    private var modifiedInstructionPointer = false
    private val _outputs = mutableListOf<Int>()
    private lateinit var _memory: MutableList<Int>
    private lateinit var currentInstruction: Instruction

    var memory: Intcode
        get() = _memory.toList()
        set(value) {
            _memory = value.toMutableList()
        }

    var inputs: List<Int> = emptyList()

    val outputs: List<Int>
        get() = _outputs

    fun compute() {
        reset()

        while (true) {
            val reader = InstructionReader(_memory, instructionPointer)
            modifiedInstructionPointer = false
            currentInstruction = reader.read()

            if (currentInstruction == Instruction.Halt) {
                break
            } else {
                currentInstruction.run()

                if (!modifiedInstructionPointer) {
                    instructionPointer += currentInstruction.parameters + 1
                }
            }
        }
    }

    operator fun set(address: Int, value: Int) {
        _memory[address] = value
    }

    private fun reset() {
        instructionPointer = 0
        inputPointer = 0
        _outputs.clear()
    }

    private fun Instruction.run() {
        return when (this) {
            is Instruction.Halt -> error("Cannot run $this")
            is Instruction.Add -> set(targetAddress, left + right)
            is Instruction.Multiply -> set(targetAddress, left * right)
            is Instruction.Input -> set(targetAddress, inputs[inputPointer++])
            is Instruction.Output -> _outputs += value
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
