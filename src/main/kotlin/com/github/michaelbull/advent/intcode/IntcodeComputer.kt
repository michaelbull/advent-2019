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

    val output: Int
        get() = _memory[OUTPUT_ADDRESS]

    var noun: Int
        get() = _memory[NOUN_ADDRESS]
        set(value) {
            require(value in NOUN_RANGE) { "noun must be in range [$NOUN_RANGE], but was $value" }
            _memory[NOUN_ADDRESS] = value
        }

    var verb: Int
        get() = _memory[VERB_ADDRESS]
        set(value) {
            require(value in VERB_RANGE) { "verb must be in range [$VERB_RANGE], but was $value" }
            _memory[VERB_ADDRESS] = value
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
                    instructionPointer += currentInstruction.opcode.parameters + 1
                }
            }
        }
    }

    private fun reset() {
        instructionPointer = 0
        inputPointer = 0
        _outputs.clear()
    }

    private fun set(address: Int, value: Int) {
        _memory[address] = value
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
            is Instruction.LessThan -> set(targetAddress, if (left < right) 1 else 0)
            is Instruction.Equals -> set(targetAddress, if (left == right) 1 else 0)
        }
    }

    private inline fun jumpIf(pointer: Int, predicate: () -> Boolean) {
        if (predicate()) {
            instructionPointer = pointer
            modifiedInstructionPointer = true
        }
    }

    companion object {
        const val OUTPUT_ADDRESS = 0
        const val NOUN_ADDRESS = 1
        const val VERB_ADDRESS = 2

        val NOUN_RANGE = 0..99
        val VERB_RANGE = 0..99
    }
}
