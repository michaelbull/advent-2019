package com.github.michaelbull.advent.day5

import com.github.michaelbull.advent.intcode.Instruction
import com.github.michaelbull.advent.intcode.InstructionReader
import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer
import com.github.michaelbull.advent.intcode.toIntcode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class Day5ExamplesTest {

    @ArgumentsSource(Part1Examples::class)
    @ParameterizedTest(name = "{0} = {1}")
    fun part1Examples(program: Intcode, expected: Instruction) {
        val reader = InstructionReader(program)
        val instruction = reader.read()
        assertEquals(expected, instruction)
    }

    @ArgumentsSource(Part2Examples::class)
    @ParameterizedTest(name = "{0} with input {1} = {2}")
    fun part2Examples(program: Intcode, input: Int, expected: Int) {
        var actual = 0

        val computer = IntcodeComputer()
        computer.onInput { if (it == 0) input else error("No input at $it") }
        computer.onOutput { actual = it }
        computer.memory = program
        computer.reset()
        computer.computeBlocking()

        assertEquals(expected, actual)
    }

    private data class Part1Arguments(
        val program: String,
        val instruction: Instruction
    ) : Arguments {
        override fun get() = arrayOf(program.toIntcode(), instruction)
    }

    private data class Part2Arguments(
        val program: String,
        val input: Int,
        val output: Int
    ) : Arguments {
        override fun get() = arrayOf(program.toIntcode(), input, output)
    }

    private class Part1Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<Part1Arguments> = Stream.of(
            Part1Arguments(program = "1002,4,3,4,33", instruction = Instruction.Multiply(33, 3, 4)),
            Part1Arguments(program = "1101,100,-1,4,0", instruction = Instruction.Add(100, -1, 4))
        )
    }

    private class Part2Examples : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<Part2Arguments> = Stream.of(

            /*
             * Using position mode, consider whether the input is equal to 8;
             * output 1 (if it is) or 0 (if it is not).
             */
            Part2Arguments(
                program = "3,9,8,9,10,9,4,9,99,-1,8",
                input = 7,
                output = 0
            ),
            Part2Arguments(
                program = "3,9,8,9,10,9,4,9,99,-1,8",
                input = 8,
                output = 1
            ),
            Part2Arguments(
                program = "3,9,8,9,10,9,4,9,99,-1,8",
                input = 9,
                output = 0
            ),

            /*
             * Using position mode, consider whether the input is less than 8;
             * output 1 (if it is) or 0 (if it is not).
             */
            Part2Arguments(
                program = "3,9,7,9,10,9,4,9,99,-1,8",
                input = 7,
                output = 1
            ),
            Part2Arguments(
                program = "3,9,7,9,10,9,4,9,99,-1,8",
                input = 8,
                output = 0
            ),
            Part2Arguments(
                program = "3,9,7,9,10,9,4,9,99,-1,8",
                input = 9,
                output = 0
            ),

            /*
             * Using immediate mode, consider whether the input is equal to 8;
             * output 1 (if it is) or 0 (if it is not).
             */
            Part2Arguments(
                program = "3,3,1108,-1,8,3,4,3,99",
                input = 7,
                output = 0
            ),
            Part2Arguments(
                program = "3,3,1108,-1,8,3,4,3,99",
                input = 8,
                output = 1
            ),
            Part2Arguments(
                program = "3,3,1108,-1,8,3,4,3,99",
                input = 9,
                output = 0
            ),

            /*
             * Using immediate mode, consider whether the input is less than 8;
             * output 1 (if it is) or 0 (if it is not).
             */
            Part2Arguments(
                program = "3,3,1107,-1,8,3,4,3,99",
                input = 7,
                output = 1
            ),
            Part2Arguments(
                program = "3,3,1107,-1,8,3,4,3,99",
                input = 8,
                output = 0
            ),
            Part2Arguments(
                program = "3,3,1107,-1,8,3,4,3,99",
                input = 9,
                output = 0
            ),

            /*
             * Here are some jump tests that take an input, then output 0 if
             * the input was zero or 1 if the input was non-zero:
             */
            Part2Arguments(
                program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9",
                input = 0,
                output = 0
            ),
            Part2Arguments(
                program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9",
                input = 10,
                output = 1
            ),
            Part2Arguments(
                program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1",
                input = 0,
                output = 0
            ),
            Part2Arguments(
                program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1",
                input = 10,
                output = 1
            ),

            /*
             * The program will then output 999 if the input value is below 8,
             * output 1000 if the input value is equal to 8, or output 1001 if
             * the input value is greater than 8.
             */
            Part2Arguments(
                program = listOf(
                    "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,",
                    "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,",
                    "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"
                ).joinToString(""),
                input = 7,
                output = 999
            ),
            Part2Arguments(
                program = listOf(
                    "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,",
                    "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,",
                    "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"
                ).joinToString(""),
                input = 8,
                output = 1000
            ),
            Part2Arguments(
                program = listOf(
                    "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,",
                    "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,",
                    "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"
                ).joinToString(""),
                input = 9,
                output = 1001
            )
        )
    }
}
