package com.github.michaelbull.advent.intcode

class IntcodeComputer {

    private var instructionPointer = 0
    private lateinit var _memory: MutableList<Int>
    private lateinit var currentInstruction: Instruction

    val memory: Intcode
        get() = _memory.toList()

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

    fun read(intcode: Intcode) {
        _memory = intcode.toMutableList()
        instructionPointer = 0
    }

    fun compute() {
        while (true) {
            currentInstruction = readInstruction()

            if (currentInstruction == Instruction.Halt) {
                break
            } else {
                currentInstruction.run()
                instructionPointer += currentInstruction.size
            }
        }
    }

    private fun getParam(offset: Int): Int {
        val address = instructionPointer + offset
        return _memory[address]
    }

    private fun dereferenceParam(offset: Int): Int {
        val address = getParam(offset)
        return _memory[address]
    }

    private fun set(address: Int, value: Int) {
        _memory[address] = value
    }

    private fun readInstruction(): Instruction {
        return when (_memory[instructionPointer].toOpcode()) {
            Opcode.Halt -> Instruction.Halt

            Opcode.Add -> Instruction.Add(
                left = dereferenceParam(1),
                right = dereferenceParam(2),
                targetAddress = getParam(3)
            )

            Opcode.Multiply -> Instruction.Multiply(
                left = dereferenceParam(1),
                right = dereferenceParam(2),
                targetAddress = getParam(3)
            )
        }
    }

    private fun Instruction.run() {
        return when (this) {
            is Instruction.Halt -> error("Cannot run $this")
            is Instruction.Add -> set(targetAddress, left + right)
            is Instruction.Multiply -> set(targetAddress, left * right)
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
